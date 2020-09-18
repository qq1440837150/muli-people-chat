package com.example.demo.controller;

import com.example.demo.domain.GroupInfo;
import com.example.demo.domain.GroupUserRelative;
import com.example.demo.domain.UserInfo;
import com.example.demo.service.GroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class GroupInfoController {
    @Autowired
    GroupInfoService groupInfoService;
    @PostMapping("/addGroup")
    public String addGroupInfo(@Valid GroupInfo groupInfo, Errors errors, Model model, HttpSession session){
        System.out.println (groupInfo.getIsPassword ());
        if(errors.hasErrors ()){
            model.addAttribute ("groupInfo",groupInfo);
            return "addGroupError";
        }
        Integer userId =((UserInfo)session.getAttribute ("userInfo")).getUserId ();
        int result = groupInfoService.insertGroupInfo (userId,groupInfo);
        if(result>0){
             return "redirect:/person";
        }else {
            model.addAttribute ("msg","群组信息录入错误");
            return "addGroupError";
        }
    }
    @GetMapping("/deleteGroup")
    public @ResponseBody String deleteGroupInfo(Integer groupId){
        Integer result =  groupInfoService.deleteGroupInfoAndGroupAndUserRelative (groupId);
        if(result>0){
            return "success";
        }else {
            return "failure";
        }
    }
    @GetMapping("/joinGroup")
    public @ResponseBody String joinGroup(Integer groupId,String password,HttpSession session){
        GroupUserRelative groupUserRelative = new GroupUserRelative ();
        groupUserRelative.setGroupId (groupId);
        groupUserRelative.setUserId (getUserInfo (session).getUserId ());
        int result = groupInfoService.joinGroup (groupUserRelative,password);
        if (result>0){
            return "success";
        }else {
            return "failure";
        }

    }
    @GetMapping("/editorGroup")
    public String editorGroup(Integer groupId,Model model,HttpSession session){
        //1 验证权限
        GroupUserRelative groupUserRelative = groupInfoService.selectGroupUserRelativeByGroupIdAndUserId (groupId,getUserInfo (session).getUserId ());
        int power = groupUserRelative.getPower ();
        if(power<=0){
            throw new RuntimeException ("查无信息");
        }
        GroupInfo groupInfo = groupInfoService.selectGroupInfoByGroupId (groupId);
        List<GroupUserRelative> groupUserRelatives = groupInfoService.selectGroupUserRelativeByGroupId (groupId);
        model.addAttribute ("groupInfo",groupInfo);
        model.addAttribute ("groupUserRelatives",groupUserRelatives);
        //2 获取群组信息
        //3 添加到model
        //4 返回视图
        return "editorGroup";
    }
    @GetMapping("/deleteGroupMember")
    public @ResponseBody  String deleteGroupMember(GroupUserRelative groupUserRelative,HttpSession session){
        // 获取操作者的权限
        GroupUserRelative operator = groupInfoService.selectGroupUserRelativeByGroupIdAndUserId (groupUserRelative.getGroupId (),getUserInfo (session).getUserId ());
        if(operator.getPower ()<=groupUserRelative.getPower ()){
            return "failure,无操作权限";
        }
//        if(groupUserRelative.getPower ())
        if(groupInfoService.deleteGroupUserRelativeByGroupIdAndUserId (groupUserRelative)>0){
            return "success";
        }else {
            return "failure";
        }
    }
    @PostMapping("/editorGroup")
    public String commitEditGroup(GroupInfo groupInfo, RedirectAttributes redirectAttributes){
        int result = groupInfoService.updateGroupInfo (groupInfo);
        if(result<=0){
            throw new RuntimeException ("更新群组信息失败");
        }
        return "redirect:/editorGroup?groupId="+groupInfo.getGroupId ();
    }
    @GetMapping("/editorGroupMember")
    public String editorGroupMember( GroupUserRelative groupUserRelative, Model model){
        model.addAttribute ("groupUserRelative",groupUserRelative);
        return "editorGroupMember";
    }
    @PostMapping("/editorGroupMember")
    public String editorGroupMemberPost(@Valid GroupUserRelative groupUserRelative, BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes, HttpSession session, Model model){
        if(bindingResult.hasErrors ()){
            return "editorGroupMember";
        }
        if(getUserInfo (session).getUserId ().equals (groupUserRelative.getUserId ())){
            model.addAttribute ("errorMsg","修改发生错误");
            return "editorGroupMember";
        }
        int result = groupInfoService.updateGroupUserRelativeByGroupIdAndUserId (groupUserRelative);
        if(result>0){
            redirectAttributes.addAttribute ("groupId",groupUserRelative.getGroupId ());
            return "redirect:/editorGroup";
        }else {
            throw new RuntimeException ("更新发生错误");
        }
    }
    @GetMapping("/testError")
    public @ResponseBody String testError(){
        throw new RuntimeException("发生了错误");
    }
    private UserInfo getUserInfo(HttpSession session){
        return (UserInfo)session.getAttribute ("userInfo");
    }
    @GetMapping("/queryGroup")
    public @ResponseBody GroupInfo queryGroupInfo(Integer groupId){
        return groupInfoService.selectGroupInfoByGroupId (groupId);
    }
    @GetMapping("/joinGroup.html")
    public String joinGroup(Model model){
        List<GroupInfo> publicGroupInfos = groupInfoService.selectGroupInfoByIsPassword (false);
        model.addAttribute ("publicGroupInfos",publicGroupInfos);
        return "joinGroup";
    }
}

package com.example.demo.controller;

import com.example.demo.domain.GroupInfo;
import com.example.demo.domain.GroupMessage;
import com.example.demo.domain.UserInfo;
import com.example.demo.service.GroupMessageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class GroupMessageController {

    @Autowired
    GroupMessageService groupMessageService;
    @Autowired
    private SimpMessageSendingOperations messageSendingOperations;
    @GetMapping("/announce")
    public @ResponseBody String announce(){
        return "";
    }
    @GetMapping("/queryMessage")
    public @ResponseBody
    List<GroupMessage> queryMessage(Integer groupId, Integer pageNum, Integer pageSize,HttpSession session){
        UserInfo userInfo = (UserInfo)session.getAttribute ("userInfo");
        Page page = PageHelper.startPage (pageNum,pageSize);
        return groupMessageService.selectGroupMessageByUserIdAndGroupId (userInfo.getUserId (),groupId);
    }
    @PostMapping("/addMessage")
    public @ResponseBody
    String addMessage(Integer groupId,String message,String picture,HttpSession session){
        UserInfo userInfo = (UserInfo)session.getAttribute ("userInfo");
        int result = groupMessageService.insertGroupMessage (userInfo.getUserId (),groupId,message,picture);
        if(result>0){
            return "success";
        }else {
            return "failure";
        }
    }
    @GetMapping("/groupChat")
    public String groupChat(Integer groupId, Model model, HttpSession session){
        model.addAttribute ("groupId",groupId);
        Integer userId = ((UserInfo)session.getAttribute ("userInfo")).getUserId ();
        model.addAttribute ("userId",userId);
        return "groupChat";
    }

}

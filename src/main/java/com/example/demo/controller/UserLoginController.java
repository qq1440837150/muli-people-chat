package com.example.demo.controller;

import com.example.demo.domain.GroupInfo;
import com.example.demo.domain.UserInfo;
import com.example.demo.service.GroupInfoService;
import com.example.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserLoginController {
    @Autowired
    GroupInfoService groupInfoService;
    @GetMapping("/person")
    public String userInfo(Model model, HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute ("userInfo");
        List<GroupInfo> groupInfos = groupInfoService.selectGroupInfoByUserId (userInfo.getUserId ());
        model.addAttribute ("userInfo",userInfo);
        model.addAttribute ("groupInfos",groupInfos);
        return "userInfo";
    }
    @Autowired
    UserInfoService userInfoService;
    @PostMapping("/login")
    public String userLogin(Integer userId, String password, Model model, HttpSession session){
        UserInfo result = userInfoService.selectUserInfoByUserIdAndPassword (userId,password);
        if(result!=null){
            session.setAttribute ("userInfo",result);
            return "redirect:/person";
        }else {
            model.addAttribute ("msg","登陆失败，请验证登陆信息");
            return "loginError";
        }
    }
    @GetMapping("/logOut")
    public String userLogOut(HttpServletRequest request){
        request.getSession ().setAttribute ("userInfo",null);
        return "redirect:/index.html";
    }
}

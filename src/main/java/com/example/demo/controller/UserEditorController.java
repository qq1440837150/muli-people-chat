package com.example.demo.controller;

import com.example.demo.domain.UserInfo;
import com.example.demo.service.UserInfoService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserEditorController {
    @Autowired
    UserInfoService userInfoService;
    @GetMapping("/userEditor")
    public String userEditor(HttpSession session, Model model){
        UserInfo userInfo = (UserInfo) session.getAttribute ("userInfo");
        model.addAttribute ("userInfo",userInfo);

        return "editorUser";
    }
    @PostMapping("/userEditor")
    public String userEditorPost(@Valid UserInfo userInfo, Errors errors, Model model, HttpSession session){
        if(errors.hasErrors ()){
            return "editorUser";
        }else {
            userInfoService.updateUserInfoByUserId (userInfo);
            session.setAttribute ("userInfo",userInfo);
        }
        return "redirect:/person";
    }
}

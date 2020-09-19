package com.example.demo.controller;

import com.example.demo.domain.UserInfo;
import com.example.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

@Controller
public class UserRegisterController {
    @Autowired
    UserInfoService service;
    @GetMapping("/register.html")
    public String registerForm(HttpSession session,Model model){
        if(session.getAttribute ("userInfo")!=null){
            return "redirect:/person";
        }
        model.addAttribute ("userInfo",new UserInfo ());
        return "register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userInfo") UserInfo userInfo, Errors errors, HttpSession session, Model model){
        if(errors.hasErrors ()){
            return "register";
        }
        if(service.insertUserInfo (userInfo)>0){
           session.setAttribute ("userInfo",userInfo);
           return "redirect:/person";
        }else {
            model.addAttribute ("msg","注册失败，请重新注册！！！");
            return "register";
        }
    }
    @PostMapping("/uploadUserPicture")
    @ResponseBody
    public String uploadPicture(MultipartFile multipartFile) throws IOException {
        File root_path = ResourceUtils.getFile ("static/img/userPicture");
        if(!root_path.exists ())root_path.mkdirs ();
        String fileName = String.format ("pic%d.jpg",System.currentTimeMillis ());
        String filePath = root_path.getAbsolutePath ()+"/"+fileName;
        multipartFile.transferTo (new File (filePath));
        return "img/userPicture/"+fileName;
    }
}

package com.example.demo.controller;

import com.example.demo.domain.GroupInfo;
import com.example.demo.domain.GroupUserRelative;
import com.example.demo.domain.Message;
import com.example.demo.domain.UserInfo;
import com.example.demo.service.GroupInfoService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.util.List;

@Controller
@Slf4j
public class MultiplyChatController {
//    @GetMapping("/enterChat")
//    public String enterChat(){
//        return "webscoket";
//    }
    @Value ("${person.setting.picture-save-path}")
    String root_path;
    @Autowired
    SimpMessageSendingOperations messageSendingOperations;
    @Autowired
    GroupInfoService groupInfoService;
    @GetMapping("/enterChat")
    public String enterChat(int groupId, Model model, HttpSession session){
//        System.out.println (groupId);
        GroupInfo groupInfo = groupInfoService.selectGroupInfoByGroupId (groupId);
//        System.out.println (groupInfo);
        model.addAttribute ("groupInfo",groupInfo);
        UserInfo userInfo = (UserInfo) session.getAttribute ("userInfo");
        model.addAttribute ("userInfo",userInfo);
        List<GroupUserRelative> groupUserRelatives = groupInfoService.selectGroupUserRelativeByGroupId (groupId);
        model.addAttribute ("groupUserRelatives",groupUserRelatives);
        return "chatTem";
    }
    @PostMapping("/uploadPicture")
    @ResponseBody public String uploadPicture(MultipartFile multipartFile) throws IOException {
        File root_path = ResourceUtils.getFile ("static/img");
        if(!root_path.exists ())root_path.mkdirs ();
        String fileName = String.format ("pic%d.jpg",System.currentTimeMillis ());
        String filePath = root_path.getAbsolutePath ()+"/"+fileName;

//        System.out.println (filePath);
        multipartFile.transferTo (new File (filePath));
//        System.out.println (new File (filePath).toURI ());
//        FileOutputStream fileOutputStream = new FileOutputStream (filePath);
//        fileOutputStream.write (multipartFile.getBytes ());
//        fileOutputStream.close ();
        return "img/"+fileName;
    }
//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/public")
//    public Message sendMessage(@Payload Message message){
//        log.info (message.toString ());
//        return message;
//    }
    @MessageMapping("/chat/sendMessage/{groupId}")
    public void sendMessage(@Payload Message message, @DestinationVariable("groupId") Integer groupId){
        log.info (message.toString ());
        messageSendingOperations.convertAndSend ("/topic/public/"+groupId,message);
    }
//    @MessageMapping("/chat/addUser/{groupId}")
//    @SendTo("/topic/public")
//    public Message addUser(@Payload Message message,
//                           SimpMessageHeaderAccessor headerAccessor,@PathVariable Integer groupId) {
//        log.info (message.toString ());
//        // Add username in web socket session
//        headerAccessor.getSessionAttributes().put("username", message.getSender());
//        return message;
//    }
    @MessageMapping("/chat/addUser/{groupId}")
    public void addUser(@Payload Message message,
                           SimpMessageHeaderAccessor headerAccessor,@DestinationVariable("groupId") Integer groupId) {
        log.info (message.toString ());
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        headerAccessor.getSessionAttributes ().put ("groupId",groupId);
        messageSendingOperations.convertAndSend ("/topic/public/"+groupId,message);
    }
}

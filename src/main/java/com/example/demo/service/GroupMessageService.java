package com.example.demo.service;

import com.example.demo.domain.GroupMessage;
import com.example.demo.mapper.GroupMessageMapper;
import com.example.demo.viewobj.GroupMessageView;

import java.util.List;

public interface GroupMessageService {
    int insertGroupMessage(Integer userId,Integer groupId,String message,String picture);
    List<GroupMessage> selectGroupMessageByUserIdAndGroupId(Integer userId, Integer groupId);
    List<GroupMessageView> selectMessageInfoByUserIdAndGroupId(Integer userId, Integer groupId);
    List<GroupMessageView> selectGroupMessageViewByGroupIdOrderByRecordTime(Integer groupId,Integer pageNum,Integer pageSize);
}

package com.example.demo.service.impl;

import com.example.demo.domain.GroupMessage;
import com.example.demo.domain.GroupUserRelative;
import com.example.demo.mapper.GroupMessageMapper;
import com.example.demo.mapper.GroupUserRelativeMapper;
import com.example.demo.service.GroupMessageService;
import com.example.demo.viewobj.GroupMessageView;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupMessageServiceImpl implements GroupMessageService {
    @Autowired
    GroupMessageMapper dao;
    @Autowired
    GroupUserRelativeMapper groupUserRelativeMapper;

    @Override
    public int insertGroupMessage(Integer userId, Integer groupId, String message, String picture) {
        GroupUserRelative groupUserRelative = groupUserRelativeMapper.selectByGroupIdAndUserId (groupId,userId);
        GroupMessage groupMessage = new GroupMessage ();
        groupMessage.setContent (message);
        groupMessage.setPicture (picture);
        groupMessage.setRelativeId (groupUserRelative.getId ());
        return dao.insertSelective (groupMessage);
    }

    @Override
    public List<GroupMessage> selectGroupMessageByUserIdAndGroupId(Integer userId, Integer groupId) {
        GroupUserRelative groupUserRelative = groupUserRelativeMapper.selectByGroupIdAndUserId (userId, groupId);
        if (groupUserRelative == null) {
            return null;
        }

        return dao.selectByRelativeIdOrderByRecordTime (groupUserRelative.getGroupId ());
    }

    @Override
    public List<GroupMessageView> selectMessageInfoByUserIdAndGroupId(Integer userId, Integer groupId) {
//        GroupMessageView groupMessageView = new GroupMessageView ();
//        GroupUserRelative groupUserRelative = groupUserRelativeMapper.selectByGroupIdAndUserId (userId, groupId);
//        if (groupUserRelative == null) {
//            return null;
//        }
//        groupMessageView.setGroupUserRelative (groupUserRelative);
//        List<GroupMessage> groupMessage = dao.selectByRelativeIdOrderByRecordTime (groupUserRelative.getGroupId ());
        return null;
    }

    @Override
    public List<GroupMessageView> selectGroupMessageViewByGroupIdOrderByRecordTime(Integer groupId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage (pageNum,pageSize);
        return dao.selectByGroupIdOrderByRecordTime (groupId);
    }
}

package com.example.demo.service.impl;

import com.example.demo.domain.GroupInfo;
import com.example.demo.domain.GroupUserRelative;
import com.example.demo.domain.UserInfo;
import com.example.demo.mapper.GroupInfoMapper;
import com.example.demo.mapper.GroupUserRelativeMapper;
import com.example.demo.service.GroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Service
public class GroupInfoServiceImpl implements GroupInfoService {
    @Autowired
    GroupUserRelativeMapper groupUserRelativeMapper;
    @Autowired
    GroupInfoMapper dao;
    @Override
    public int insertGroupInfo(GroupInfo groupInfo) {
        return dao.insertSelective (groupInfo);
    }

    @Override
    public GroupUserRelative selectGroupUserRelativeByGroupIdAndUserId(Integer groupId, Integer userId) {
        return groupUserRelativeMapper.selectByGroupIdAndUserId (groupId,userId);
    }

    @Override
    public List<GroupUserRelative> selectGroupUserRelativeByGroupId(Integer groupId) {
        return groupUserRelativeMapper.selectByGroupId (groupId);
    }

    @Override
    public GroupInfo selectGroupInfoByGroupId(Integer groupId) {
        return dao.selectByGroupId (groupId);
    }

    @Override
    public int updateGroupInfo(GroupInfo groupInfo) {

        return dao.updateByGroupId (groupInfo);
    }

    @Override
    public int deleteGroupUserRelativeByGroupIdAndUserId(GroupUserRelative groupUserRelative) {

        return groupUserRelativeMapper.deleteByGroupIdAndUserId (groupUserRelative.getGroupId (),groupUserRelative.getUserId ());
    }

    @Override
    public int updateGroupUserRelativeByGroupIdAndUserId(GroupUserRelative groupUserRelative) {

        return groupUserRelativeMapper.updateByGroupIdAndUserId (groupUserRelative);
    }

    @Override
    public List<GroupInfo> selectGroupInfoByIsPassword(Boolean isPassword) {

        return dao.selectByIsPassword (isPassword);
    }



    @Override
    public int joinGroup(GroupUserRelative groupUserRelative, String password) {
        int result = 0;
        GroupInfo groupInfo = dao.selectByGroupId (groupUserRelative.getGroupId ());
        if (groupInfo != null) {
            if (groupInfo.getIsPassword ()) {
                if(password.equals (groupInfo.getPassword ())){
                    result = groupUserRelativeMapper.insert (groupUserRelative);
                }
            }else {
                result = groupUserRelativeMapper.insert (groupUserRelative);
            }
        }

        return result;
    }

    @Override
    public List<GroupInfo> selectGroupInfoByUserId(Integer userId) {
        return dao.selectByUserId (userId);
    }

    @Override
    public int insertGroupInfo(Integer userId, GroupInfo groupInfo) {
        int result = 0;
        try {
            if(groupInfo.getPassword ().equals ("")){
                groupInfo.setPassword (null);
            }
            GroupUserRelative groupUserRelative = new GroupUserRelative ();
            result = dao.insertSelective (groupInfo);
            groupUserRelative.setPower (2);
            groupUserRelative.setGroupId (groupInfo.getGroupId ());
            groupUserRelative.setUserId (userId);
            groupUserRelativeMapper.insert (groupUserRelative);
        }catch (Exception e){
            System.out.println ("【重】"+e.getMessage ());
        }

        return result;
    }

    @Override
    @Transient
    public int deleteGroupInfoAndGroupAndUserRelative(Integer groupId) {
        groupUserRelativeMapper.deleteByGroupId (groupId);
        Integer result = dao.deleteByGroupId (groupId);
        return result;
    }

}

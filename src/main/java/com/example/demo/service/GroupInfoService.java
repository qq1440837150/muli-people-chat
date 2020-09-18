package com.example.demo.service;

import com.example.demo.domain.GroupInfo;
import com.example.demo.domain.GroupUserRelative;
import com.example.demo.domain.UserInfo;

import java.util.List;

public interface GroupInfoService {
    int insertGroupInfo(GroupInfo groupInfo);
    int insertGroupInfo(Integer userId,GroupInfo groupInfo);
    List<GroupInfo> selectGroupInfoByUserId(Integer userId);
    GroupInfo selectGroupInfoByGroupId(Integer groupId);
    int deleteGroupInfoAndGroupAndUserRelative(Integer groupId);
    int joinGroup(GroupUserRelative groupUserRelative,String password);
    GroupUserRelative selectGroupUserRelativeByGroupIdAndUserId(Integer groupId,Integer userId);
    List<GroupUserRelative> selectGroupUserRelativeByGroupId(Integer groupId);
    int updateGroupInfo(GroupInfo groupInfo);
    int deleteGroupUserRelativeByGroupIdAndUserId(GroupUserRelative groupUserRelative);
    int updateGroupUserRelativeByGroupIdAndUserId(GroupUserRelative groupUserRelative);
    List<GroupInfo> selectGroupInfoByIsPassword(Boolean isPassword);
}

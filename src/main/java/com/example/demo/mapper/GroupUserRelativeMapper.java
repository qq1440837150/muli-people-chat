package com.example.demo.mapper;

import com.example.demo.domain.GroupUserRelative;

import java.util.List;

public interface GroupUserRelativeMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByGroupId(Integer groupId);

    int insert(GroupUserRelative record);

    int insertSelective(GroupUserRelative record);

    GroupUserRelative selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupUserRelative record);

    int updateByPrimaryKey(GroupUserRelative record);

    GroupUserRelative selectByGroupIdAndUserId(Integer groupId,Integer userId);

    List<GroupUserRelative> selectByGroupId(Integer groupId);

    int deleteByGroupIdAndUserId(Integer groupId,Integer userId);
    int updateByGroupIdAndUserId(GroupUserRelative groupUserRelative);
}
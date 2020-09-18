package com.example.demo.mapper;

import com.example.demo.domain.GroupInfo;

import java.util.List;

public interface GroupInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByGroupId(Integer groupId);

    int insert(GroupInfo record);

    int insertSelective(GroupInfo record);

    GroupInfo selectByPrimaryKey(Integer id);
    List<GroupInfo> selectByIsPassword(Boolean isPassword);

    int updateByPrimaryKeySelective(GroupInfo record);


    int updateByPrimaryKey(GroupInfo record);
    int updateByGroupId(GroupInfo groupInfo);
    List<GroupInfo> selectByUserId(Integer userId);
    GroupInfo selectByGroupId(Integer groupId);
}
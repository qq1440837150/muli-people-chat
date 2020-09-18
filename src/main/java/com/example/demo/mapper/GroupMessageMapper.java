package com.example.demo.mapper;

import com.example.demo.domain.GroupMessage;
import com.example.demo.viewobj.GroupMessageView;

import java.util.List;

public interface GroupMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupMessage record);

    int insertSelective(GroupMessage record);

    GroupMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupMessage record);

    int updateByPrimaryKey(GroupMessage record);

    List<GroupMessage> selectByRelativeIdOrderByRecordTime(Integer relativeId);
    List<GroupMessageView> selectByGroupIdOrderByRecordTime(Integer groupId);
}
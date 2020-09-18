package com.example.demo.mapper;

import com.example.demo.domain.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    int updateByUserId(UserInfo userInfo);
    UserInfo selectUserInfoByUserIdAndPassword(Integer userId,String password);

}
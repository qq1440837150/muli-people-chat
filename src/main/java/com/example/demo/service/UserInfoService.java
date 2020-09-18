package com.example.demo.service;

import com.example.demo.domain.UserInfo;

public interface UserInfoService {
    int insertUserInfo(UserInfo userInfo);
    UserInfo selectUserInfoByUserIdAndPassword(Integer userId,String password);
    int updateUserInfoByUserId(UserInfo userInfo);
}

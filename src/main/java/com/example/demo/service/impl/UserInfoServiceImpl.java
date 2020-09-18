package com.example.demo.service.impl;

import com.example.demo.domain.UserInfo;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper dao;
    @Override
    public int insertUserInfo(UserInfo userInfo) {
        int result;
        try{
            result = dao.insert (userInfo);
        }catch (Exception e){
            result = 0;
        }
        return result;
    }

    @Override
    public UserInfo selectUserInfoByUserIdAndPassword(Integer userId, String password) {

        return dao.selectUserInfoByUserIdAndPassword (userId,password);
    }

    @Override
    public int updateUserInfoByUserId(UserInfo userInfo) {
        return dao.updateByUserId (userInfo);
    }

}

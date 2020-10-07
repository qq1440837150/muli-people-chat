package com.example.demo;

import com.example.demo.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class TestRepository {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Test
    public void testUserInfo(){
        log.info (userInfoRepository.findAll ().toString ());
    }
}

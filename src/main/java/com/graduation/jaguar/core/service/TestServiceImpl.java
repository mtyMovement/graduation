package com.graduation.jaguar.core.service;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/4/22 0022
*/

import com.graduation.jaguar.core.dal.manager.impl.UserManagerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private UserManagerImpl userManager;

    @Override
    public void testSQLConnection() {

        log.info(userManager.queryUserInfo().toString());

    }
}

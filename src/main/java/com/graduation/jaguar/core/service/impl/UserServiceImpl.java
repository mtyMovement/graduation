package com.graduation.jaguar.core.service.impl;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/17 0017
*/

import com.graduation.jaguar.core.dal.domain.User;
import com.graduation.jaguar.core.dal.manager.impl.UserManagerImpl;
import com.graduation.jaguar.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserManagerImpl userManager;
    @Override
    public User getUserById(Integer userId) {

        return userManager.selectById(userId);
    }
}

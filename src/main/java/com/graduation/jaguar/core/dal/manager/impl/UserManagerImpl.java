package com.graduation.jaguar.core.dal.manager.impl;

import com.graduation.jaguar.core.dal.domain.User;
import com.graduation.jaguar.core.dal.dao.UserDao;
import com.graduation.jaguar.core.dal.manager.UserManager;
import com.graduation.jaguar.core.common.base.BaseManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserManagerImpl extends BaseManagerImpl<UserDao, User> implements UserManager{

    @Autowired
    UserDao userDao;

    @Override
    public String getUserNameById(Integer userId) {
        if(Objects.nonNull(userId)){
            User user = userDao.selectById(userId);
            return user.getUserName();
        }
        return "";
    }
}

package com.graduation.jaguar.core.dal.manager.impl;

import com.graduation.jaguar.core.dal.domain.User;
import com.graduation.jaguar.core.dal.dao.UserDao;
import com.graduation.jaguar.core.dal.manager.UserManager;
import com.graduation.jaguar.core.common.base.BaseManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserManagerImpl extends BaseManagerImpl<UserDao, User> implements UserManager{

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> queryUserInfo() {
        User user = new User();
        user.setUserId(1);
        List<User> userList = new ArrayList<>();
        userList.add(userDao.selectOne(user));
        return userList;
    }
}

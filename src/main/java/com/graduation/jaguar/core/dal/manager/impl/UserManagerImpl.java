package com.graduation.jaguar.core.dal.manager.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.graduation.jaguar.core.common.enums.UserTypeEnum;
import com.graduation.jaguar.core.common.util.DateUtil;
import com.graduation.jaguar.core.dal.domain.User;
import com.graduation.jaguar.core.dal.dao.UserDao;
import com.graduation.jaguar.core.dal.manager.UserManager;
import com.graduation.jaguar.core.common.base.BaseManagerImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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

    @Override
    public Integer normalGetTodayCount() {
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.between("gmt_create", DateUtil.getTodayBeginDate(), DateUtil.getTodayEndDate());
        return userDao.selectCount(wrapper);
    }

    @Override
    public boolean isUserNoExist(String userNo) {
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("user_no", userNo);
        if(CollectionUtils.isNotEmpty(userDao.selectList(wrapper))){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<User> queryUserInfo() {
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("user_isdelete", 0)
                .ne("user_type", UserTypeEnum.ADMIN.getCode());
        return userDao.selectList(wrapper);
    }
}

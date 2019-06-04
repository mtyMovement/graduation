package com.graduation.jaguar.core.dal.manager;

import com.graduation.jaguar.core.dal.domain.User;
import com.graduation.jaguar.core.common.base.BaseManager;

import java.util.List;

public interface UserManager extends BaseManager<User> {
    String getUserNameById(Integer userId);

    Integer normalGetTodayCount();

    /**
     * 用户名判重
     * @param userNo
     * @return
     */
    boolean isUserNoExist(String userNo);

    /**
     * 获取用户信息
     * @return
     */
    List<User> queryUserInfo();
}

package com.graduation.jaguar.core.dal.manager;

import com.graduation.jaguar.core.dal.domain.User;
import com.graduation.jaguar.core.common.base.BaseManager;

import java.util.List;

public interface UserManager extends BaseManager<User> {

    public List<User> queryUserInfo();

}

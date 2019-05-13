package com.graduation.jaguar.core.dal.manager;

import com.graduation.jaguar.core.dal.domain.User;
import com.graduation.jaguar.core.common.base.BaseManager;

public interface UserManager extends BaseManager<User> {
    String getUserNameById(Integer userId);
}

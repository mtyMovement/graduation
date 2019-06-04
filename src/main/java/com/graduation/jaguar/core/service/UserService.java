package com.graduation.jaguar.core.service;/*
 @author maoty
 @DESCRIPTION 用户信息
 @create 2019/5/17 0017
*/

import com.graduation.jaguar.core.common.DTO.UserRegisterDTO;
import com.graduation.jaguar.core.common.entity.APIResult;
import com.graduation.jaguar.core.dal.domain.User;

import java.util.List;

public interface UserService {
    /**
     * 根据 id 获取 user 信息
     * @param userId
     * @return
     */
    User getUserById(Integer userId);

    /**
     * 根据 name 获取 user 信息
     * @param userName
     * @return
     */
    User getUserByName(String userName);

    /**
     * 根据 No 获取 user 信息
     * @param userNo
     * @return
     */
    User getUserByNo(String userNo);

    APIResult<String> userRegister(UserRegisterDTO userRegisterDTO);

    User normalFirstLogin();

    /**
     * 获取感兴趣的类型
     * @param userId
     * @return
     */
    List<String> getInterestTypesByUserId(Integer userId);

    /**
     * 获取关注用户
     * @param userId
     * @return
     */
    List<Integer> getSubscribeUsersByUserId(Integer userId);
}

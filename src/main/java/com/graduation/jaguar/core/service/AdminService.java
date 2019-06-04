package com.graduation.jaguar.core.service;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/22 0022
*/

import com.graduation.jaguar.core.common.VO.ClassifyInfoVO;
import com.graduation.jaguar.core.common.VO.UserInfoVO;
import com.graduation.jaguar.core.common.entity.APIResult;

import java.util.List;

public interface AdminService {
    APIResult<List<UserInfoVO>> queryUserInfo();

    APIResult<List<UserInfoVO>> queryUserInfo(String userName, String userType);

    APIResult deleteUser(Integer userId);

    APIResult<List<ClassifyInfoVO>> queryClassifyInfo();

    APIResult<List<ClassifyInfoVO>> queryClassifyInfo(String classifyName, String classifyCode);

    APIResult addClassifyInfo(String classifyName, String classifyCode);
}

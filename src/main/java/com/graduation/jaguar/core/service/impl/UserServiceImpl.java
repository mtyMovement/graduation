package com.graduation.jaguar.core.service.impl;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/17 0017
*/

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.graduation.jaguar.core.common.DTO.UserRegisterDTO;
import com.graduation.jaguar.core.common.entity.APIResult;
import com.graduation.jaguar.core.common.enums.OperateTypeEnum;
import com.graduation.jaguar.core.common.enums.UserTypeEnum;
import com.graduation.jaguar.core.common.util.UUIDGenerator;
import com.graduation.jaguar.core.dal.domain.Operation;
import com.graduation.jaguar.core.dal.domain.User;
import com.graduation.jaguar.core.dal.manager.OperationManager;
import com.graduation.jaguar.core.dal.manager.impl.UserManagerImpl;
import com.graduation.jaguar.core.service.ClassifyService;
import com.graduation.jaguar.core.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserManagerImpl userManager;
    @Autowired
    ClassifyService classifyService;
    @Autowired
    OperationManager operationManager;

    @Override
    public User getUserById(Integer userId) {

        return userManager.selectById(userId);
    }

    @Override
    public User getUserByName(String userName) {
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("user_name",userName);
        return userManager.selectOne(wrapper);
    }

    @Override
    public User getUserByNo(String userNo) {
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("user_no",userNo);
        return userManager.selectOne(wrapper);
    }

    @Override
    public APIResult<String> userRegister(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setUserName(userRegisterDTO.getUserName());
        user.setUserType(UserTypeEnum.REGISTER_USER.getCode());
        userRegisterDTO.setUserType(UserTypeEnum.REGISTER_USER.getCode());
        if(StringUtils.isNotBlank(userRegisterDTO.getUserInterest())){
            user.setUserInterest(userRegisterDTO.getUserInterest());
        }
        if(userRegisterDTO.getUserType() == 0){
            //根据当天有颗登录人数累加，自动生成游客账号
            Calendar calendar = Calendar.getInstance();
            int count = userManager.normalGetTodayCount();
            String userNo = "N" + "" + calendar.get(Calendar.YEAR)
                    + String.format("%02d",calendar.get(Calendar.MONTH) + 1)
                    + calendar.get(Calendar.DAY_OF_MONTH) + String.format("%04d",count + 1);
            user.setUserNo(userNo);
        }else if(userRegisterDTO.getUserType() == 5){
            user.setUserNo("S" + UUIDGenerator.getUUID(5));
            user.setUserPwd(userRegisterDTO.getUserPwd());
            while(userManager.isUserNoExist(user.getUserNo())){
                user.setUserNo("S" + UUIDGenerator.getUUID(5));
            }
        }
        userManager.insert(user);
        return APIResult.ok(user.getUserNo());
    }

    @Override
    public User normalFirstLogin() {
        User user = new User();
        //根据当天有颗登录人数累加，自动生成游客账号
        Calendar calendar = Calendar.getInstance();
        int count = userManager.normalGetTodayCount();
        String userNo = "N" + "" + calendar.get(Calendar.YEAR)
                + String.format("%02d",calendar.get(Calendar.MONTH) + 1)
                + calendar.get(Calendar.DAY_OF_MONTH) + String.format("%04d",count + 1);
        user.setUserNo(userNo);
        user.setUserName(userNo);
        user.setUserPwd("0");
        user.setUserType(UserTypeEnum.NORMAL_USER.getCode());
        userManager.insert(user);
        return user;
    }

    @Override
    public List<String> getInterestTypesByUserId(Integer userId) {
        User user = userManager.selectById(userId);
        List<String> interestType = new ArrayList<>();
        if(StringUtils.isNotBlank(user.getUserInterest())){
            interestType = Arrays.asList(user.getUserInterest().split(","));
        }
        return interestType;
    }

    @Override
    public List<Integer> getSubscribeUsersByUserId(Integer userId) {
        EntityWrapper<Operation> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id",userId)
                .eq("operate_type", OperateTypeEnum.OPERATE_SUBSCRIBE_USER.getCode())
                .eq("is_valid", 1);
        List<Operation> operationList = operationManager.selectList(wrapper);
        List<Integer> userIdList = operationList.stream()
                .map(Operation::getDiffTypeId)
                .collect(Collectors.toList());
        return userIdList;
    }
}

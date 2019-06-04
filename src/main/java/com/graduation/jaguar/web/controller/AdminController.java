package com.graduation.jaguar.web.controller;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/22 0022
*/

import com.graduation.jaguar.core.common.VO.ClassifyInfoVO;
import com.graduation.jaguar.core.common.VO.UserInfoVO;
import com.graduation.jaguar.core.common.entity.APIResult;
import com.graduation.jaguar.core.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/userManagerQueryInfo")
    @ResponseBody
    public APIResult<List<UserInfoVO>> userManagerQueryInfo(){
        return adminService.queryUserInfo();
    }

    @GetMapping("/userManagerSearchInfo")
    @ResponseBody
    public APIResult<List<UserInfoVO>> userManagerSearchInfo(String userName, String userType){
        return adminService.queryUserInfo(userName, userType);
    }

    @GetMapping("/userManagerDeleteUser")
    @ResponseBody
    public APIResult userManagerDeleteUser(Integer userId){
        return adminService.deleteUser(userId);
    }

    @GetMapping("/classifyManagerQueryInfo")
    @ResponseBody
    public APIResult<List<ClassifyInfoVO>> classifyManagerQueryInfo(){
        return adminService.queryClassifyInfo();
    }

    @GetMapping("/classifyManagerSearchInfo")
    @ResponseBody
    public APIResult<List<ClassifyInfoVO>> classifyManagerQueryInfo(String classifyName, String classifyCode){
        return adminService.queryClassifyInfo(classifyName,classifyCode);
    }

    @GetMapping("/classifyManagerAddInfo")
    @ResponseBody
    public APIResult classifyManagerAddInfo(String classifyName, String classifyCode){
        return adminService.addClassifyInfo(classifyName,classifyCode);
    }

}

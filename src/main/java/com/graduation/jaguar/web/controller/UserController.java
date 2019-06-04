package com.graduation.jaguar.web.controller;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/17 0017
*/

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.graduation.jaguar.core.common.DTO.UserRegisterDTO;
import com.graduation.jaguar.core.common.entity.APIResult;
import com.graduation.jaguar.core.common.enums.UserTypeEnum;
import com.graduation.jaguar.core.common.util.DateUtil;
import com.graduation.jaguar.core.common.util.TokenService;
import com.graduation.jaguar.core.dal.domain.User;
import com.graduation.jaguar.core.service.ClassifyService;
import com.graduation.jaguar.core.service.UserService;
import com.graduation.jaguar.web.annotation.NeedAuth;
import com.graduation.jaguar.web.annotation.NoAuth;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    ClassifyService classifyService;

    //登录
    @NoAuth
    @GetMapping("/login")
    @ResponseBody
    public APIResult login(HttpServletResponse httpServletResponse, @RequestParam String userId, @RequestParam String userPwd){
        log.info("进入loginController");
        User userForBase = userService.getUserByNo(userId);
        if(userForBase == null){
            return APIResult.error("600", "用户名不存在");
        }else {
            if (!userForBase.getUserPwd().equals(userPwd)){
                return APIResult.error("601", "登录失败,密码错误");
            }else if(Objects.equals(userForBase.getUserType(), UserTypeEnum.ADMIN.getCode())){
                String token = tokenService.getToken(userForBase);
                Cookie cookie = new Cookie("token",token);
                cookie.setPath("/");           //可在同一应用服务器内共享cookie
                httpServletResponse.addCookie(cookie);
                APIResult apiResult = APIResult.ok();
                apiResult.setCode("100");
                return apiResult;
            } else{
                String token = tokenService.getToken(userForBase);
                Cookie cookie = new Cookie("token",token);
                cookie.setPath("/");           //可在同一应用服务器内共享cookie
                httpServletResponse.addCookie(cookie);
                return APIResult.ok();
            }
        }
    }

    @NoAuth
    @GetMapping("/normalLogin")
    @ResponseBody
    public APIResult<String> normalLogin(HttpServletResponse httpServletResponse, String userNo){
        User user = userService.getUserByNo(userNo);
        if(Objects.isNull(user)){
            return APIResult.error("940", "游客号不正确, 请重新输入");
        }else if(!Objects.equals(user.getUserType(), UserTypeEnum.NORMAL_USER.getCode())){
            return APIResult.error("941", "用户类型不正确, 请重新输入");
        }
        else{
            String token = tokenService.getToken(user);
            Cookie cookie = new Cookie("token",token);
            cookie.setPath("/");           //可在同一应用服务器内共享cookie
            httpServletResponse.addCookie(cookie);
            return APIResult.ok();
        }
    }

    @NoAuth
    @GetMapping("/normalFirstLogin")
    @ResponseBody
    public APIResult<User> normalFirstLogin(HttpServletResponse httpServletResponse){
        User user = userService.normalFirstLogin();
        if(Objects.isNull(user)){
            APIResult.error("330", "游客登录失败");
        }
        String token = tokenService.getToken(user);
        Cookie cookie = new Cookie("token",token);
        cookie.setPath("/");           //可在同一应用服务器内共享cookie
        httpServletResponse.addCookie(cookie);
        return APIResult.ok(user);
    }


    @NoAuth
    @PostMapping("/register")
    @ResponseBody
    public APIResult userRegister(UserRegisterDTO userRegisterDTO){
        if(StringUtils.isBlank(userRegisterDTO.getUserName()) || StringUtils.isBlank(userRegisterDTO.getUserPwd())){
            return APIResult.error("302","请输入用户名及密码");
        }
        String userName = userRegisterDTO.getUserName();
        User user = userService.getUserByName(userName);
        if(Objects.nonNull(user)){
            return APIResult.error("300","该用户名已存在，请重新输入");
        }
        return userService.userRegister(userRegisterDTO);
    }

    @NeedAuth
    @GetMapping("/getMessage")
    @ResponseBody
    public String getMessage(@CookieValue("token") String token){
        String userId = JWT.decode(token).getAudience().get(0);
        System.out.println(JWT.decode(token));
        System.out.println(JWT.decode(token).getAudience());
        Date date = DateUtil.getTodayEndDate();
        String dateString = DateUtil.parseToString(date,DateUtil.NORMAL_PATTERN);
        return "你已通过验证 userId:" + userId + dateString;
    }
}

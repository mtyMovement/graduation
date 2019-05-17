package com.graduation.jaguar.web.controller;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/17 0017
*/

import com.alibaba.fastjson.JSONObject;
import com.graduation.jaguar.core.common.util.TokenService;
import com.graduation.jaguar.core.dal.domain.User;
import com.graduation.jaguar.core.service.UserService;
import com.graduation.jaguar.web.annotation.NeedAuth;
import com.graduation.jaguar.web.annotation.NoAuth;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    //登录
    @GetMapping("/login")
    @ResponseBody
    public Object login(HttpServletResponse httpServletResponse, @RequestParam Integer userId, @RequestParam String userPwd){
        JSONObject jsonObject=new JSONObject();
        User userForBase=userService.getUserById(userId);
        if(userForBase==null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            if (!userForBase.getUserPwd().equals(userPwd)){
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = tokenService.getToken(userForBase);
                Cookie cookie = new Cookie("token",token);
                cookie.setPath("/");           //可在同一应用服务器内共享cookie
                httpServletResponse.addCookie(cookie);
                return "index";
            }
        }
    }
    @NeedAuth
    @GetMapping("/getMessage")
    @ResponseBody
    public String getMessage(){
        return "你已通过验证";
    }
}

package com.graduation.jaguar.web.controller;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/4/22 0022
*/

import com.graduation.jaguar.core.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/testController")
    public String testController(){
        //testController.html
        testService.testSQLConnection();
        return "testController.html";
    }

    @RequestMapping("/login")
    public String loginController(){
        return "index";
    }
}

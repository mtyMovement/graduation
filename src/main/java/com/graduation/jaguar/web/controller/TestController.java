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
        return "videoTest";
    }

    @RequestMapping("/testQiniu")
    public String qiniuController() throws Exception{
        testService.testQiniuConnection();
        return "testController";
    }

    @RequestMapping("/login")
    public String loginController(){
        return "login";
    }

    @RequestMapping("/single")
    public String singleTest(){
        return "single";
    }

    @RequestMapping("/index")
    public String indexTest(){
        return "index";
    }

    @RequestMapping("/gallery")
    public String galleryTest(){
        return "gallery";
    }

    @RequestMapping("/contact")
    public String contactTest(){
        return "contact";
    }
}

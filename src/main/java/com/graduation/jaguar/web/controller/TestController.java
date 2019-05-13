package com.graduation.jaguar.web.controller;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/4/22 0022
*/

import com.graduation.jaguar.core.common.DTO.VideoUploadDTO;
import com.graduation.jaguar.core.common.VO.VideoInfoVO;
import com.graduation.jaguar.core.common.entity.APIResult;
import com.graduation.jaguar.core.service.TestService;
import com.graduation.jaguar.core.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    TestService testService;
    @Autowired
    VideoService videoService;

    @RequestMapping("/testController")
    public String testController() throws Exception{
        //testController.html
        //testService.testSQLConnection();

        /*VideoUploadDTO videoUploadDTO = new VideoUploadDTO();
        videoUploadDTO.setUserId(1);
        videoUploadDTO.setVideoAddress("E:/work/video/我害怕.mp4");
        videoService.uplaodVideo(videoUploadDTO);*/


        log.info(videoService.getVideoInfo(5).toString());
        return "testController";
    }

    @RequestMapping("/testSQL")
    public String testSQL(){
        //testController.html
        testService.testSQLConnection();
        return "testController";
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

    @PostMapping("/testSwitch")
    @ResponseBody
    public APIResult<VideoInfoVO> testSwitch(@RequestBody Integer videoId){
        return videoService.getVideoInfo(videoId);
    }
}

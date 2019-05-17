package com.graduation.jaguar.web.controller;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/4/22 0022
*/

import com.alibaba.fastjson.JSON;
import com.graduation.jaguar.core.common.DTO.VideoUploadDTO;
import com.graduation.jaguar.core.common.VO.VideoInfoVO;
import com.graduation.jaguar.core.common.entity.APIResult;
import com.graduation.jaguar.core.common.enums.OperateTypeEnum;
import com.graduation.jaguar.core.service.TestService;
import com.graduation.jaguar.core.service.VideoService;
import com.graduation.jaguar.web.annotation.NeedAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@NeedAuth
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

        VideoUploadDTO videoUploadDTO = new VideoUploadDTO();
        videoUploadDTO.setUserId(1);
        videoUploadDTO.setVideoAddress("E:/work/video/儿时.mp4");
        videoService.uplaodVideo(videoUploadDTO);

        //log.info(videoService.getVideoInfo(5).toString());
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

    @GetMapping("/upVideo")
    public String uploadVideo(@RequestParam String videoName) throws Exception {
        VideoUploadDTO videoUploadDTO = new VideoUploadDTO();
        videoUploadDTO.setUserId(1);
        videoUploadDTO.setVideoAddress("E:/work/video/" + videoName + ".mp4");
        log.info("uploadVideo,videoName:{},videoUploadDTO:{}",videoName, videoUploadDTO);
        videoService.uplaodVideo(videoUploadDTO);
        return "testController";
    }

    @RequestMapping("/testInterest")
    public String testInterest(){
        videoService.operateRecord(1,5, OperateTypeEnum.OPERATE_VIDEO_INTEREST);
        return "testController";
    }

    @GetMapping("/initSingle")
    public String initSingle(Model model,@RequestParam Integer videoId){
        VideoInfoVO video = videoService.getVideoInfo(videoId).getData();
        model.addAttribute("video", JSON.toJSONString(video));
        log.info("videoId:{},model:{}",videoId,model);
        return "single";
    }
}

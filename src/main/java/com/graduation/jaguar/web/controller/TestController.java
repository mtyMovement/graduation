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
import com.graduation.jaguar.core.dal.domain.Classify;
import com.graduation.jaguar.core.service.ClassifyService;
import com.graduation.jaguar.core.service.TestService;
import com.graduation.jaguar.core.service.VideoService;
import com.graduation.jaguar.web.annotation.NeedAuth;
import com.graduation.jaguar.web.annotation.NoAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    TestService testService;
    @Autowired
    VideoService videoService;
    @Autowired
    ClassifyService classifyService;

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

    @NeedAuth
    @RequestMapping("/single")
    public String singleTest(){
        return "single";
    }

    @NeedAuth
    @RequestMapping("/index")
    public String indexTest(){
        return "index";
    }

    @NeedAuth
    @RequestMapping("/gallery")
    public String galleryTest(){
        return "gallery";
    }

    @NeedAuth
    @RequestMapping("/contact")
    public String contactTest(){
        return "contact";
    }

    @RequestMapping("/forms")
    public String formsTest(){
        return "forms";
    }

    @NeedAuth
    @PostMapping("/testSwitch")
    @ResponseBody
    public APIResult<VideoInfoVO> testSwitch(@RequestBody Integer videoId){
        return videoService.getVideoInfo(videoId);
    }

    @NeedAuth
    @GetMapping("/upVideo")
    public String uploadVideo(@RequestParam String videoName) throws Exception {
        VideoUploadDTO videoUploadDTO = new VideoUploadDTO();
        videoUploadDTO.setUserId(1);
        videoUploadDTO.setVideoAddress("E:/work/video/" + videoName + ".mp4");
        log.info("uploadVideo,videoName:{},videoUploadDTO:{}",videoName, videoUploadDTO);
        videoService.uplaodVideo(videoUploadDTO);
        return "testController";
    }

    @NeedAuth
    @RequestMapping("/testInterest")
    public String testInterest(){
        videoService.operateRecord(1,5, OperateTypeEnum.OPERATE_VIDEO_INTEREST,"");
        return "testController";
    }

    @NeedAuth
    @GetMapping("/initSingle")
    public String initSingle(Model model,@RequestParam Integer videoId){
        VideoInfoVO video = videoService.getVideoInfo(videoId).getData();
        model.addAttribute("video", JSON.toJSONString(video));
        log.info("videoId:{},model:{}",videoId,model);
        return "single";
    }

    @NeedAuth
    @GetMapping("/initPublicVideoSingle")
    public String initPublicVideoSingle(Model model,@RequestParam Integer videoId){
        VideoInfoVO video = videoService.getVideoInfo(videoId).getData();
        model.addAttribute("video", JSON.toJSONString(video));
        log.info("videoId:{},model:{}",videoId,model);
        return "PublicVideoSingle";
    }

    @NoAuth
    @GetMapping("/testClassify")
    public String testClassify(){
        Classify classify = new Classify();
        classify.setClassifyName("美食");
        String code = String.format("%04d", 5);
        classify.setClassifyCode(code);
        classifyService.addNewClassify(classify);
        return "testController";
    }

    @NoAuth
    @RequestMapping("/normalLogin")
    public String login1Test(){
        return "normalLogin";
    }

    @NoAuth
    @RequestMapping("/register")
    public String registerTest(){
        return "register";
    }

    @NoAuth
    @RequestMapping("/userManager")
    public String userManagerTest(){
        return "userManager";
    }

    @NoAuth
    @RequestMapping("/classifyManager")
    public String classifyManagerTest(){
        return "classifyManager";
    }
}

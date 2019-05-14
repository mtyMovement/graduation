package com.graduation.jaguar.web.controller;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/14 0014
*/

import com.graduation.jaguar.core.common.VO.VideoInfoVO;
import com.graduation.jaguar.core.common.entity.APIResult;
import com.graduation.jaguar.core.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("video")
public class VideoController {
    @Autowired
    VideoService videoService;

    @GetMapping("/initPageSingle")
    @ResponseBody
    public APIResult<Map> initPageSingle(){
        Map result = new HashMap();
        APIResult hottestAPIResult = videoService.getHottestVideoInfos(4);
        if(!hottestAPIResult.isSuccess()){
            return APIResult.error(hottestAPIResult.getCode(),hottestAPIResult.getMessage());
        }else{
            result.put("hottestResult",hottestAPIResult.getData());
        }
        APIResult newestAPIResult = videoService.getNewestVideoInfos(3);
        if(!newestAPIResult.isSuccess()){
            return APIResult.error(newestAPIResult.getCode(),newestAPIResult.getMessage());
        }else{
            result.put("newestResult",newestAPIResult.getData());
        }
        //TODO: 返回标签和相关视频   js:抽出方法，放到js文件
        return APIResult.ok(result);
    }

    @PostMapping("/switchVideo")
    @ResponseBody
    public APIResult<VideoInfoVO> switchVideo(@RequestBody Integer videoId){
        return videoService.getVideoInfo(videoId);
    }
}

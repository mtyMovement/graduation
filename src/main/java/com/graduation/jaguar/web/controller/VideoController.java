package com.graduation.jaguar.web.controller;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/14 0014
*/

import com.graduation.jaguar.core.common.VO.VideoInfoVO;
import com.graduation.jaguar.core.common.entity.APIResult;
import com.graduation.jaguar.core.common.enums.OperateTypeEnum;
import com.graduation.jaguar.core.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @GetMapping("/initPageIndex")
    @ResponseBody
    public APIResult<Map> initPageIndex(){
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

        APIResult mostViewedAPIResult = videoService.getHottestVideoInfos(5);
        if(!newestAPIResult.isSuccess()){
            return APIResult.error(newestAPIResult.getCode(),newestAPIResult.getMessage());
        }else{
            result.put("mostViewedResult",mostViewedAPIResult.getData());
        }

        return APIResult.ok(result);
    }

    @PostMapping("/switchVideo")
    @ResponseBody
    public APIResult<VideoInfoVO> switchVideo(@RequestBody Integer videoId){
        return videoService.getVideoInfo(videoId);
    }

    @GetMapping("/operateRecord")
    @ResponseBody
    public APIResult<Integer> operateRecord(
            @RequestParam Integer userId, @RequestParam Integer videoId, @RequestParam Integer operateType){
        if(Objects.isNull(userId) || Objects.isNull(videoId) || Objects.isNull(operateType)){
            return APIResult.error("264","数据不全");
        }
        //操作类型 0-视频点赞  1-视频分享  2-评论点赞
        if(Objects.equals(operateType,0)){
            return videoService.operateRecord(userId, videoId, OperateTypeEnum.OPERATE_VIDEO_INTEREST);
        }else if(Objects.equals(operateType,1)){
            return videoService.operateRecord(userId, videoId, OperateTypeEnum.OPERATE_VIDEO_SHARE);
        }else if(Objects.equals(operateType,2)){
            return videoService.operateRecord(userId, videoId, OperateTypeEnum.OPERATE_COMMENT_INTEREST);
        }
        else{
            return APIResult.error("265","操作类型错误");
        }
    }
}

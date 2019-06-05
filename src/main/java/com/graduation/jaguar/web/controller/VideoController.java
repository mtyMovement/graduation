package com.graduation.jaguar.web.controller;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/14 0014
*/

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.plugins.Page;
import com.graduation.jaguar.core.common.VO.VideoInfoVO;
import com.graduation.jaguar.core.common.entity.APIResult;
import com.graduation.jaguar.core.common.enums.OperateTypeEnum;
import com.graduation.jaguar.core.dal.domain.User;
import com.graduation.jaguar.core.service.UserService;
import com.graduation.jaguar.core.service.VideoService;
import com.graduation.jaguar.web.annotation.NeedAuth;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("video")
public class VideoController {
    @Autowired
    VideoService videoService;
    @Autowired
    UserService userService;

    @NeedAuth
    @GetMapping("/initPageSingle")
    @ResponseBody
    public APIResult<Map> initPageSingle(Integer videoId){
        Map result = new HashMap();

        APIResult hottestAPIResult = videoService.getHottestVideoInfos(3);
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

        APIResult commentAPIResult = videoService.queryCommentByVideoId(videoId);
        if(!commentAPIResult.isSuccess()){
            return APIResult.error(commentAPIResult.getCode(),commentAPIResult.getMessage());
        }else{
            result.put("commentAPIResult",commentAPIResult.getData());
        }

        return APIResult.ok(result);
    }


    @NeedAuth
    @GetMapping("/initPageGallery")
    @ResponseBody
    public APIResult<Map> initPageGallery(@CookieValue("token") String token){
        //获取token内的userid
        Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
        Map result = new HashMap();
        //分页信息
        Page page = new Page();
        page.setSize(4);
        page.setCurrent(1);

        APIResult hottestAPIResult = videoService.getHottestVideoInfos(3);
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

        APIResult auditAPIResult = videoService.getAuditVideoBuUserId(page, userId);
        if(!auditAPIResult.isSuccess()){
            return APIResult.error(auditAPIResult.getCode(),auditAPIResult.getMessage());
        }else{
            result.put("auditAPIResult",auditAPIResult.getData());
        }

        APIResult publicAPIResult = videoService.getPublicVideoBuUserId(page, userId);
        if(!publicAPIResult.isSuccess()){
            return APIResult.error(publicAPIResult.getCode(),publicAPIResult.getMessage());
        }else{
            result.put("publicAPIResult",publicAPIResult.getData());
        }

        APIResult shareAPIResult = videoService.getShareVideoBuUserId(page, userId);
        if(!shareAPIResult.isSuccess()){
            return APIResult.error(shareAPIResult.getCode(),shareAPIResult.getMessage());
        }else{
            result.put("shareAPIResult",shareAPIResult.getData());
        }

        APIResult interestAPIResult = videoService.getInterestVideoBuUserId(page, userId);
        if(!interestAPIResult.isSuccess()){
            return APIResult.error(interestAPIResult.getCode(),interestAPIResult.getMessage());
        }else{
            result.put("interestAPIResult",interestAPIResult.getData());
        }

        return APIResult.ok(result);
    }

    @NeedAuth
    @GetMapping("/initPageIndex")
    @ResponseBody
    public APIResult<Map> initPageIndex(@CookieValue("token") String token){
        //获取token内的userid
        Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
        //根据id查询关注类型
        List<String> interestTypeList = userService.getInterestTypesByUserId(userId);
        //根据id获取关注用户id
        List<Integer> userIdList = userService.getSubscribeUsersByUserId(userId);

        Map result = new HashMap();
        APIResult hottestAPIResult = videoService.getHottestVideoInfos(3);
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
        if(!mostViewedAPIResult.isSuccess()){
            return APIResult.error(mostViewedAPIResult.getCode(),mostViewedAPIResult.getMessage());
        }else{
            result.put("mostViewedResult",mostViewedAPIResult.getData());
        }

        APIResult interestedVideoAPIResult = videoService.getInterestedVideoInfos(interestTypeList,8);
        if(!newestAPIResult.isSuccess()){
            return APIResult.error(interestedVideoAPIResult.getCode(),interestedVideoAPIResult.getMessage());
        }else{
            result.put("interestedVideoAPIResult",interestedVideoAPIResult.getData());
        }

        APIResult subscribeUserVideoAPIResult = videoService.getSubscribeVideoInfos(userIdList,8);
        if(!newestAPIResult.isSuccess()){
            return APIResult.error(subscribeUserVideoAPIResult.getCode(),subscribeUserVideoAPIResult.getMessage());
        }else{
            result.put("subscribeUserVideoAPIResult",subscribeUserVideoAPIResult.getData());
        }

        return APIResult.ok(result);
    }

    @NeedAuth
    @PostMapping("/switchVideo")
    @ResponseBody
    public APIResult<VideoInfoVO> switchVideo(@RequestBody Integer videoId){
        return videoService.getVideoInfo(videoId);
    }

    @NeedAuth
    @GetMapping("/operateRecord")
    @ResponseBody
    public APIResult<Integer> operateRecord(@CookieValue("token") String token,
                                            Integer diffTypeId, Integer operateType, String content){
        Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
        if(Objects.isNull(userId) || Objects.isNull(diffTypeId) || Objects.isNull(operateType)){
            return APIResult.error("264","数据不全");
        }
        User user = userService.getUserById(userId);
        if(user.getUserType() == 0){
            return APIResult.error("267","游客用户不可进行本次操作，请注册");
        }
        //操作类型 0-视频点赞  1-视频分享  2-评论点赞  3-评论视频  4-关注用户
        if(Objects.equals(operateType,0)){
            return videoService.operateRecord(userId, diffTypeId, OperateTypeEnum.OPERATE_VIDEO_INTEREST,"");
        }else if(Objects.equals(operateType,1)){
            return videoService.operateRecord(userId, diffTypeId, OperateTypeEnum.OPERATE_VIDEO_SHARE,content);
        }else if(Objects.equals(operateType,2)){
            return videoService.operateRecord(userId, diffTypeId, OperateTypeEnum.OPERATE_COMMENT_INTEREST,"");
        }else if(Objects.equals(operateType,3)){
            if(StringUtils.isBlank(content)){
                return APIResult.error("266","评论内容不可以为空");
            }
            return videoService.operateRecord(userId, diffTypeId, OperateTypeEnum.OPERATE_VIDEO_COMMENT,content);
        }
        else if(Objects.equals(operateType,4)){
            return videoService.operateRecord(userId, diffTypeId, OperateTypeEnum.OPERATE_SUBSCRIBE_USER,content);
        }
        else{
            return APIResult.error("265","操作类型错误");
        }
    }

    @NeedAuth
    @GetMapping("/searchVideoByName")
    @ResponseBody
    public APIResult<List<VideoInfoVO>> searchVideoByName(String videoName){
        return videoService.searchVideoByName(videoName);
    }

    @NeedAuth
    @GetMapping("/changeAuditVideoPage")
    @ResponseBody
    public APIResult<Page<VideoInfoVO>> changeAuditVideoPage(@CookieValue("token") String token
            , Integer pageCurrent, Integer pageSize){
        //获取token内的userid
        Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));

        //分页信息
        Page page = new Page();
        page.setSize(pageSize);
        page.setCurrent(pageCurrent);

        return videoService.getAuditVideoBuUserId(page, userId);
    }

    @NeedAuth
    @GetMapping("/changePublicVideoPage")
    @ResponseBody
    public APIResult<Page<VideoInfoVO>> changePublicVideoPage(@CookieValue("token") String token
            , Integer pageCurrent, Integer pageSize){
        //获取token内的userid
        Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));

        //分页信息
        Page page = new Page();
        page.setSize(pageSize);
        page.setCurrent(pageCurrent);

        return videoService.getPublicVideoBuUserId(page, userId);
    }

    @NeedAuth
    @GetMapping("/changeShareVideoPage")
    @ResponseBody
    public APIResult<Page<VideoInfoVO>> changeShareVideoPage(@CookieValue("token") String token
            , Integer pageCurrent, Integer pageSize){
        //获取token内的userid
        Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));

        //分页信息
        Page page = new Page();
        page.setSize(pageSize);
        page.setCurrent(pageCurrent);

        return videoService.getShareVideoBuUserId(page, userId);
    }

    @NeedAuth
    @GetMapping("/changeInterestVideoPage")
    @ResponseBody
    public APIResult<Page<VideoInfoVO>> changeInterestVideoPage(@CookieValue("token") String token
            , Integer pageCurrent, Integer pageSize){
        //获取token内的userid
        Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));

        //分页信息
        Page page = new Page();
        page.setSize(pageSize);
        page.setCurrent(pageCurrent);

        return videoService.getInterestVideoBuUserId(page, userId);
    }

    @NeedAuth
    @GetMapping("/personDeleteVideo")
    @ResponseBody
    public APIResult personDeleteVideo(Integer videoId){
        return videoService.personDeleteVideo(videoId);
    }

    @NeedAuth
    @GetMapping("/typeVideoQueryInfo")
    @ResponseBody
    public APIResult typeVideoQueryInfo(String typeName){
        Map result = new HashMap();
        APIResult hottestAPIResult = videoService.getHottestVideoInfos(3);
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

        APIResult typeVideoAPIResult = videoService.typeVideoQueryInfo(typeName);
        if(!typeVideoAPIResult.isSuccess()){
            return APIResult.error(typeVideoAPIResult.getCode(),typeVideoAPIResult.getMessage());
        }else{
            result.put("typeVideoAPIResult",typeVideoAPIResult.getData());
        }

        return APIResult.ok(result);
    }
}

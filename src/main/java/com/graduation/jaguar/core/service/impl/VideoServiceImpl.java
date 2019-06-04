package com.graduation.jaguar.core.service.impl;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/12 0012
*/

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.graduation.jaguar.core.common.DTO.VideoUploadDTO;
import com.graduation.jaguar.core.common.VO.VideoInfoVO;
import com.graduation.jaguar.core.common.entity.APIResult;
import com.graduation.jaguar.core.common.enums.OperateTypeEnum;
import com.graduation.jaguar.core.common.enums.VideoAuditStatusEnum;
import com.graduation.jaguar.core.common.util.DateUtil;
import com.graduation.jaguar.core.common.util.QiniuService;
import com.graduation.jaguar.core.dal.domain.Operation;
import com.graduation.jaguar.core.dal.domain.Video;
import com.graduation.jaguar.core.dal.manager.impl.OperationManagerImpl;
import com.graduation.jaguar.core.dal.manager.impl.UserManagerImpl;
import com.graduation.jaguar.core.dal.manager.impl.VideoManagerImpl;
import com.graduation.jaguar.core.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    QiniuService qiniuService;

    @Autowired
    VideoManagerImpl videoManager;

    @Autowired
    UserManagerImpl userManager;

    @Autowired
    OperationManagerImpl operationManager;

    @Override
    @Transactional
    public APIResult uplaodVideo(VideoUploadDTO videoUploadDTO) throws Exception{
        //数据处理  根据文件路径截取文件名及视频名称 (区别在于有无后缀名)
        String address = videoUploadDTO.getVideoAddress();
        String fileName = address.substring(address.lastIndexOf("/") + 1);
        String videoName = fileName.substring(0,fileName.indexOf("."));
        //设置 video 实体类数据
        Video video = new Video();
        video.setUserId(videoUploadDTO.getUserId());
        video.setVideoName(videoName);
        video.setVideoUrl(qiniuService.buildFileUrl(fileName));
        //上传到七牛云 由于同样名称会被覆盖，所以需要判重
        if(qiniuService.isExist(fileName)){
            return APIResult.error("250","该文件名已存在，请修改文件名");
        }
        //上传视频的同时截取对应尺寸的图片，默认为jpg格式
        qiniuService.uploadFile(videoUploadDTO.getVideoAddress());
        log.info("testQiniuConnection:开始获取视频截图");
        qiniuService.qiNiuMediaPrtScreen(fileName,"jpg","854" , "478");
        videoManager.insert(video);

        return APIResult.ok();
    }

    @Override
    public APIResult<VideoInfoVO> getVideoInfo(Integer videoId) {
        Video video = videoManager.selectVideoByVideoId(videoId);
        VideoInfoVO videoInfoVO = buildVideoInfoVO(video);
        log.info("获取 video 信息, videoId:{},videoInfo：{}", video, videoInfoVO);
        return APIResult.ok(videoInfoVO);
    }

    @Override

    public APIResult<List<VideoInfoVO>> getHottestVideoInfos(Integer limitNum) {
        List<Video> videoList =  videoManager.getSatisfactoryVideo("video_interested",false,limitNum);
        List<VideoInfoVO> videoInfoVOS = videoList.stream()
                .map(videoValue -> buildVideoInfoVO(videoValue))
                .collect(Collectors.toList());
        if(CollectionUtils.isEmpty(videoInfoVOS)){
            return APIResult.error("252","未查找到视频");
        }
        return APIResult.ok(videoInfoVOS);
    }

    @Override
    public APIResult<List<VideoInfoVO>> getInterestedVideoInfos(List<String> interestTypes, Integer limitNum) {
        EntityWrapper<Video> wrapper = new EntityWrapper<>();
        wrapper.in("video_classify", interestTypes)
                .eq("is_deleted", 0)
                .orderBy("video_audit_time")
                .last("limit " + limitNum);
        List<Video> videoList =  videoManager.selectList(wrapper);
        List<VideoInfoVO> videoInfoVOS = videoList.stream()
                .map(videoValue -> buildVideoInfoVO(videoValue))
                .collect(Collectors.toList());
        if(CollectionUtils.isNotEmpty(videoInfoVOS)){
            return APIResult.ok(videoInfoVOS);
        }
        return APIResult.ok();
    }

    @Override
    public APIResult<List<VideoInfoVO>> getSubscribeVideoInfos(List<Integer> userIdList, Integer limitNum) {
        EntityWrapper<Video> wrapper = new EntityWrapper<>();
        wrapper.in("user_id", userIdList)
                .eq("is_deleted", 0)
                .orderBy("video_audit_time", false)
                .last("limit " + limitNum);
        List<Video> videoList =  videoManager.selectList(wrapper);
        List<VideoInfoVO> videoInfoVOS = videoList.stream()
                .map(videoValue -> buildVideoInfoVO(videoValue))
                .collect(Collectors.toList());
        if(CollectionUtils.isEmpty(videoInfoVOS)){
            return APIResult.ok(videoInfoVOS);
        }
        return APIResult.ok();
    }

    @Override
    public APIResult<List<VideoInfoVO>> getNewestVideoInfos(Integer limitNum) {
        List<Video> videoList =  videoManager.getSatisfactoryVideo("video_audit_time",false,limitNum);
        List<VideoInfoVO> videoInfoVOS = videoList.stream()
                .map(videoValue -> buildVideoInfoVO(videoValue))
                .collect(Collectors.toList());
        if(CollectionUtils.isEmpty(videoInfoVOS)){
            return APIResult.error("252","未查找到视频");
        }
        return APIResult.ok(videoInfoVOS);
    }

    @Override
    public APIResult<Integer> operateRecord(Integer userId, Integer diffTypeId,
                                            OperateTypeEnum operateTypeEnum, String remark) {
        Integer msg = -1;
        //视频点赞  1-  2-评论点赞  3-  4-
        if(Objects.nonNull(operateTypeEnum) && Objects.equals(operateTypeEnum,OperateTypeEnum.OPERATE_VIDEO_INTEREST)){
            msg = operationManager.interestVideo(userId, diffTypeId);
        }
        //视频分享
        else if(Objects.nonNull(operateTypeEnum) && Objects.equals(operateTypeEnum,OperateTypeEnum.OPERATE_VIDEO_SHARE)){
            msg = operationManager.shareVideo(userId, diffTypeId,remark);
        }
        //评论视频
        else if(Objects.nonNull(operateTypeEnum) && Objects.equals(operateTypeEnum,OperateTypeEnum.OPERATE_VIDEO_COMMENT)){
            msg = operationManager.commentVideo(userId, diffTypeId,remark);
        }
        //关注用户
        else if(Objects.nonNull(operateTypeEnum) && Objects.equals(operateTypeEnum,OperateTypeEnum.OPERATE_SUBSCRIBE_USER)){
            msg = operationManager.subscribeUser(userId, diffTypeId);
        }
        //返回值判断
        if(msg == -1){
            return APIResult.error("254","记录信息有误，请联系管理员修改");
        }else if(msg == -2){
            return APIResult.error("255","已经分享过该视频，请勿重复分享");
        }else if(msg == -3){
            return APIResult.error("256","已关注该用户");
        }else{
            return APIResult.ok(msg);
        }
    }

    @Override
    public APIResult<List<VideoInfoVO>> searchVideoByName(String videoName) {
        EntityWrapper<Video> wrapper = new EntityWrapper<>();
        wrapper.like("video_name", videoName);
        List<Video> videoList =  videoManager.selectList(wrapper);
        List<VideoInfoVO> videoInfoVOList = new ArrayList<>();
        if(CollectionUtils.isEmpty(videoList)){
            return APIResult.error("350","未找到相关视频");
        }else {
            videoInfoVOList = videoList.stream().map(value -> buildVideoInfoVO(value))
                    .collect(Collectors.toList());
        }
        return APIResult.ok(videoInfoVOList);
    }

    @Override
    public APIResult<List<Map<String, String>>> queryCommentByVideoId(Integer videoId) {
        List<Map<String, String>> resultList = new ArrayList<>();

        EntityWrapper<Operation> wrapper = new EntityWrapper<>();
        wrapper.eq("diff_type_id", videoId)
                .eq("operate_type", OperateTypeEnum.OPERATE_VIDEO_COMMENT.getCode())
                .eq("is_valid", 1)
                .orderBy("create_time", false);
        List<Operation> operationList = operationManager.selectList(wrapper);
        operationList.stream()
                .forEach(value -> {
                    Map<String, String> mapValue = new HashMap<>();
                    String userName = userManager.selectById(value.getUserId()).getUserName();
                    mapValue.put("user_Name", userName);
                    mapValue.put("comment", value.getRemark());
                    resultList.add(mapValue);
                });
        if(CollectionUtils.isNotEmpty(resultList)){
            return APIResult.ok(resultList);
        }

        return APIResult.ok();
    }

    @Override
    public APIResult<Page<VideoInfoVO>> getAuditVideoBuUserId(Page page, Integer userId) {
        EntityWrapper<Video> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId)
                .eq("video_audit_status", VideoAuditStatusEnum.PENDING_AUDIT.getCode())
                .eq("is_deleted", 0);
        Page<Video> videoPage = videoManager.selectPage(page, wrapper);
        if(Objects.nonNull(videoPage.getRecords())){
            List<Video> videoList = videoPage.getRecords();
            List<VideoInfoVO> videoInfoVOList = videoList.stream()
                    .map(value -> buildVideoInfoVO(value))
                    .collect(Collectors.toList());
            Page<VideoInfoVO> videoInfoPage = new Page<>();
            videoInfoPage.setCurrent(videoPage.getCurrent());
            videoInfoPage.setSize(videoPage.getSize());
            videoInfoPage.setTotal(videoPage.getTotal());
            videoInfoPage.setRecords(videoInfoVOList);
            return APIResult.ok(videoInfoPage);
        }

        return APIResult.ok();
    }

    @Override
    public APIResult<Page<VideoInfoVO>> getPublicVideoBuUserId(Page page, Integer userId) {
        EntityWrapper<Video> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId)
                .eq("video_audit_status", VideoAuditStatusEnum.PASSED_AUDIT.getCode())
                .eq("is_deleted", 0);
        Page<Video> videoPage = videoManager.selectPage(page, wrapper);
        if(Objects.nonNull(videoPage.getRecords())){
            List<Video> videoList = videoPage.getRecords();
            List<VideoInfoVO> videoInfoVOList = videoList.stream()
                    .map(value -> buildVideoInfoVO(value))
                    .collect(Collectors.toList());
            Page<VideoInfoVO> videoInfoPage = new Page<>();
            videoInfoPage.setCurrent(videoPage.getCurrent());
            videoInfoPage.setSize(videoPage.getSize());
            videoInfoPage.setTotal(videoPage.getTotal());
            videoInfoPage.setRecords(videoInfoVOList);
            return APIResult.ok(videoInfoPage);
        }

        return APIResult.ok();
    }

    @Override
    public APIResult<Page<VideoInfoVO>> getShareVideoBuUserId(Page page, Integer userId) {
        EntityWrapper<Operation> wrapperOpe = new EntityWrapper<>();
        wrapperOpe.eq("user_id", userId)
                .eq("operate_type", OperateTypeEnum.OPERATE_VIDEO_SHARE.getCode())
                .eq("is_valid", 1);
        List<Operation> operationList = operationManager.selectList(wrapperOpe);
        List<Integer> videoIdList = operationList.stream()
                .map(Operation::getDiffTypeId)
                .collect(Collectors.toList());

        EntityWrapper<Video> wrapper = new EntityWrapper<>();
        wrapper.in("video_id", videoIdList)
                .eq("video_audit_status", VideoAuditStatusEnum.PASSED_AUDIT.getCode())
                .eq("is_deleted", 0);
        Page<Video> videoPage = videoManager.selectPage(page, wrapper);
        if(Objects.nonNull(videoPage.getRecords())){
            List<Video> videoList = videoPage.getRecords();
            List<VideoInfoVO> videoInfoVOList = videoList.stream()
                    .map(value -> buildVideoInfoVO(value))
                    .collect(Collectors.toList());
            Page<VideoInfoVO> videoInfoPage = new Page<>();
            videoInfoPage.setCurrent(videoPage.getCurrent());
            videoInfoPage.setSize(videoPage.getSize());
            videoInfoPage.setTotal(videoPage.getTotal());
            videoInfoPage.setRecords(videoInfoVOList);
            return APIResult.ok(videoInfoPage);
        }

        return APIResult.ok();
    }

    @Override
    public APIResult<Page<VideoInfoVO>> getInterestVideoBuUserId(Page page, Integer userId) {
        EntityWrapper<Operation> wrapperOpe = new EntityWrapper<>();
        wrapperOpe.eq("user_id", userId)
                .eq("operate_type", OperateTypeEnum.OPERATE_VIDEO_INTEREST.getCode())
                .eq("is_valid", 1);
        List<Operation> operationList = operationManager.selectList(wrapperOpe);
        List<Integer> videoIdList = operationList.stream()
                .map(Operation::getDiffTypeId)
                .collect(Collectors.toList());

        EntityWrapper<Video> wrapper = new EntityWrapper<>();
        wrapper.in("video_id", videoIdList)
                .eq("video_audit_status", VideoAuditStatusEnum.PASSED_AUDIT.getCode())
                .eq("is_deleted", 0);
        Page<Video> videoPage = videoManager.selectPage(page, wrapper);
        if(Objects.nonNull(videoPage.getRecords())){
            List<Video> videoList = videoPage.getRecords();
            List<VideoInfoVO> videoInfoVOList = videoList.stream()
                    .map(value -> buildVideoInfoVO(value))
                    .collect(Collectors.toList());
            Page<VideoInfoVO> videoInfoPage = new Page<>();
            videoInfoPage.setCurrent(videoPage.getCurrent());
            videoInfoPage.setSize(videoPage.getSize());
            videoInfoPage.setTotal(videoPage.getTotal());
            videoInfoPage.setRecords(videoInfoVOList);
            return APIResult.ok(videoInfoPage);
        }

        return APIResult.ok();
    }

    @Override
    public APIResult personDeleteVideo(Integer videoId) {
        Video video = videoManager.selectById(videoId);
        video.setIsDeleted(1);
        video.setModifyTime(null);
        if(Objects.equals(videoManager.updateById(video), 0) ){
            return APIResult.error("801", "删除失败");
        }

        return APIResult.ok();
    }

    public VideoInfoVO buildVideoInfoVO(Video video){
        VideoInfoVO videoInfoVO = new VideoInfoVO();
        String userName = userManager.getUserNameById(video.getUserId());
        try {
            BeanUtils.copyProperties(videoInfoVO, video);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //设置视频过审时间
        String videoAuditTime = DateUtil.parseToString(video.getVideoAuditTime(),"yyyy/MM/dd");
        videoInfoVO.setVideoAuditTime(videoAuditTime);
        //根据用户id获取用户昵称
        if(StringUtils.isEmpty(userName)){
            return null;
        }
        videoInfoVO.setUserName(userName);
        videoInfoVO.setUserId(video.getUserId());
        String videoJPGName = video.getVideoName() + ".jpg";
        videoInfoVO.setVideoJPGUrl(qiniuService.buildFileUrl(videoJPGName));
        return videoInfoVO;
    }
}

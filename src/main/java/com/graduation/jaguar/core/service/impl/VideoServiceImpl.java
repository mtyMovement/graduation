package com.graduation.jaguar.core.service.impl;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/12 0012
*/

import com.graduation.jaguar.core.common.DTO.VideoUploadDTO;
import com.graduation.jaguar.core.common.VO.VideoInfoVO;
import com.graduation.jaguar.core.common.entity.APIResult;
import com.graduation.jaguar.core.common.util.DateUtil;
import com.graduation.jaguar.core.common.util.QiniuService;
import com.graduation.jaguar.core.dal.domain.Video;
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
import java.util.List;
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

        String videoJPGName = video.getVideoName() + ".jpg";
        videoInfoVO.setVideoJPGUrl(qiniuService.buildFileUrl(videoJPGName));
        return videoInfoVO;
    }
}

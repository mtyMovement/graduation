package com.graduation.jaguar.core.service;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/12 0012
*/

import com.graduation.jaguar.core.common.DTO.VideoUploadDTO;
import com.graduation.jaguar.core.common.VO.VideoInfoVO;
import com.graduation.jaguar.core.common.entity.APIResult;

import java.util.List;

public interface VideoService {
    /**
     * 上传视频, 同时生成视频截图
     * @param videoUploadDTO
     * @return
     * @throws Exception
     */
    APIResult uplaodVideo(VideoUploadDTO videoUploadDTO) throws Exception;

    /**
     * 根据视频 id 获取视频详细信息
     * @param videoId
     * @return
     */
    APIResult<VideoInfoVO> getVideoInfo(Integer videoId);

    /**
     * 获取当前最热的视频
     * @return
     */
    APIResult<List<VideoInfoVO>> getHottestVideoInfos(Integer limitNum);

    /**
     * 获取当前最新的视频
     * @return
     */
    APIResult<List<VideoInfoVO>> getNewestVideoInfos(Integer limitNum);
}

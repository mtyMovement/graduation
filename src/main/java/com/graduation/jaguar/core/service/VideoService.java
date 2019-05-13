package com.graduation.jaguar.core.service;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/12 0012
*/

import com.graduation.jaguar.core.common.DTO.VideoUploadDTO;
import com.graduation.jaguar.core.common.VO.VideoInfoVO;
import com.graduation.jaguar.core.common.entity.APIResult;

public interface VideoService {
    APIResult uplaodVideo(VideoUploadDTO videoUploadDTO) throws Exception;

    APIResult<VideoInfoVO> getVideoInfo(Integer videoId);
}

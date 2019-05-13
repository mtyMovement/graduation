package com.graduation.jaguar.core.common.DTO;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/12 0012
*/

import lombok.Data;

@Data
public class VideoUploadDTO {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 视频上传地址
     */
    private String videoAddress;
}

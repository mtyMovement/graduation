package com.graduation.jaguar.core.common.VO;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/13 0013
*/

import lombok.Data;


@Data
public class VideoInfoVO {

    /**
     * 主键id
     */
    private Integer videoId;

    /**
     * 视频名称
     */
    private String videoName;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 视频url
     */
    private String videoUrl;

    /**
     * 视频截图url
     */
    private String videoJPGUrl;

    /**
     * 视频收藏数
     */
    private Integer videoInterested;

    /**
     * 审核时间
     */
    private String videoAuditTime;

}

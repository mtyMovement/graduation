package com.graduation.jaguar.core.common.enums;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/15 0015
*/

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperateTypeEnum implements BaseEnum {
    OPERATE_VIDEO_INTEREST(0,"视频点赞"),
    OPERATE_VIDEO_SHARE(1,"视频分享"),
    OPERATE_COMMENT_INTEREST(2,"评论点赞")

    ;

    private Integer code;
    private String desc;
}

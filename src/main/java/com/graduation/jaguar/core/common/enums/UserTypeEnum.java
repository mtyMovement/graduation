package com.graduation.jaguar.core.common.enums;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/20 0020
*/

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserTypeEnum {
    NORMAL_USER(0,"普通用户"),
    REGISTER_USER(5,"注册会员"),
    ADMIN(10,"管理员")

    ;

    private Integer code;
    private String desc;
}

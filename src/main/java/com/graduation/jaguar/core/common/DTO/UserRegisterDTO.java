package com.graduation.jaguar.core.common.DTO;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/18 0018
*/

import lombok.Data;

import java.util.List;

@Data
public class UserRegisterDTO {

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户类型  0-普通账户  5-注册会员
     */
    private Integer userType;

    /**
     * 用户密码  普通账户可为空
     */
    private String userPwd;

    /**
     * 用户感兴趣的类型
     */
    //@JsonProperty("serialNumber")
    private String userInterest;
}

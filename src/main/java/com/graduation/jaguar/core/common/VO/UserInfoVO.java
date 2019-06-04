package com.graduation.jaguar.core.common.VO;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/22 0022
*/


import lombok.Data;

import java.util.Date;

@Data
public class UserInfoVO {
    /**
     * user表主键
     */
    private Integer userId;
    /**
     * 用户账号
     */
    private String userNo;
    /**
     * 用户类型  0-普通账户  5-注册会员  10-管理员
     */
    private String userType;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 用户感兴趣的类型
     */
    private String userInterest;
    /**
     * 创建时间
     */
    private String createTime;
}

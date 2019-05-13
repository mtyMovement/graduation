package com.graduation.jaguar.core.dal.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.graduation.jaguar.core.common.base.BaseModel;
import com.baomidou.mybatisplus.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * user表主键
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    /**
     * 用户账号  自动生成
     */
    @TableField("user_no")
    private String userNo;
    /**
     * 用户类型  0-普通账户  5-注册会员  10-管理员
     */
    @TableField("user_type")
    private Integer userType;
    /**
     * 用户密码  普通账户可为空
     */
    @TableField("user_pwd")
    private String userPwd;
    /**
     * 用户昵称
     */
    @TableField("user_name")
    private String userName;
    /**
     * 用户是否逻辑删除 0-未删除  1-已删除
     */
    @TableField("user_isdelete")
    private Integer userIsdelete;
    /**
     * 用户感兴趣的类型
     */
    @TableField("user_interest")
    private String userInterest;
    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @TableField("gmt_modified")
    private Date gmtModified;

}

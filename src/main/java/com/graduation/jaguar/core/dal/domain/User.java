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
     * userè¡¨ä¸»é”®
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    /**
     * ç”¨æˆ·è´¦å·  è‡ªåŠ¨ç”Ÿæˆ
     */
    @TableField("user_no")
    private String userNo;
    /**
     * ç”¨æˆ·ç±»åž‹  0-æ™®é€šè´¦æˆ·  5-æ³¨å†Œä¼šå‘˜  10-ç®¡ç†å‘˜
     */
    @TableField("user_type")
    private Integer userType;
    /**
     * ç”¨æˆ·å¯†ç   æ™®é€šè´¦æˆ·å¯ä¸ºç©º
     */
    @TableField("user_pwd")
    private String userPwd;
    /**
     * ç”¨æˆ·æ˜µç§°
     */
    @TableField("user_name")
    private String userName;
    /**
     * ç”¨æˆ·æ˜¯å¦é€»è¾‘åˆ é™¤ 0-æœªåˆ é™¤  1-å·²åˆ é™¤
     */
    @TableField("user_isdelete")
    private Integer userIsdelete;
    /**
     * ç”¨æˆ·æ„Ÿå…´è¶£çš„ç±»åž‹
     */
    @TableField("user_interest")
    private String userInterest;
    /**
     * åˆ›å»ºæ—¶é—´
     */
    @TableField("gmt_create")
    private Date gmtCreate;
    /**
     * ä¿®æ”¹æ—¶é—´
     */
    @TableField("gmt_modified")
    private Date gmtModified;

}

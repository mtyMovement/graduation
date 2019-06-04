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
public class Video extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @TableId(value = "video_id", type = IdType.AUTO)
    private Integer videoId;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 视频名称
     */
    @TableField("video_name")
    private String videoName;
    /**
     * 视频url
     */
    @TableField("video_url")
    private String videoUrl;
    /**
     * 视频收藏数
     */
    @TableField("video_interested")
    private Integer videoInterested;
    /**
     * 0-待审核  1-审核通过  2-审核不通过
     */
    @TableField("video_audit_status")
    private String videoAuditStatus;
    /**
     * 审核时间
     */
    @TableField("video_audit_time")
    private Date videoAuditTime;
    /**
     * 类别(二进制)
     */
    @TableField("video_classify")
    private String videoClassify;
    /**
     * 0-未删除  1-已删除
     */
    @TableField("is_deleted")
    private Integer isDeleted;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("modify_time")
    private Date modifyTime;

}

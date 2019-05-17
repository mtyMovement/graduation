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
public class Operation extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @TableId(value = "operation_id", type = IdType.AUTO)
    private Integer operationId;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 被操作内容id
     */
    @TableField("diff_type_id")
    private Integer diffTypeId;
    /**
     * 操作类型  0-视频点赞  1-视频分享  2-评论点赞
     */
    @TableField("operate_type")
    private Integer operateType;
    /**
     * 备注  分享理由等
     */
    private String remark;
    /**
     * 是否生效  0-有效  1-无效
     */
    @TableField("is_valid")
    private Integer isValid;
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

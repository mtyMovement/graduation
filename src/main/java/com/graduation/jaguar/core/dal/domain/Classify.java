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
public class Classify extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @TableId(value = "classify_id", type = IdType.AUTO)
    private Integer classifyId;
    /**
     * 类别名称
     */
    @TableField("classify_name")
    private String classifyName;
    /**
     * 类别编码
     */
    @TableField("classify_code")
    private String classifyCode;
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

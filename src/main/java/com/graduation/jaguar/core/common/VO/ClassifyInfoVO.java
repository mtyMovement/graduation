package com.graduation.jaguar.core.common.VO;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/22 0022
*/

import lombok.Data;

@Data
public class ClassifyInfoVO {
    /**
     * 主键id
     */
    private Integer classifyId;

    /**
     * 类别名称
     */
    private String classifyName;

    /**
     * 类别编码
     */
    private String classifyCode;

    /**
     * 创建时间
     */
    private String createTime;
}

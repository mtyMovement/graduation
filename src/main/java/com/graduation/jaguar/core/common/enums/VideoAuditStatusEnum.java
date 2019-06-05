package com.graduation.jaguar.core.common.enums;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/13 0013
*/

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审核状态枚举
 */
@Getter
@AllArgsConstructor
public enum VideoAuditStatusEnum implements BaseEnum {
    PENDING_AUDIT(0,"待审核"),
    PASSED_AUDIT(1,"审核通过"),
    UNAUDITED(2,"审核不通过")

    ;

    private Integer code;
    private String desc;
}

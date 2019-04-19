package com.graduation.jaguar.core.common.enums;

import lombok.Getter;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Package com.sxx.sivir.core.common.enums
 * @Author: 尚星
 * @Date: 2019/4/18 10:48
 * @Copyright: 2018-2099  ShangXing - Sivir All rights reserved.
 * @Description: 订单类型枚举
 * 0-待揽件，1-已通知快递员，2-快递员已揽件，3-在途，4-配送中，5-已完成，9-作废
 */
public enum OrderTypeEnum {

    WAIT_GET(0,"待揽件"),
    NOTICE_TRANS(1,"已通知快递员"),
    HAS_GOTTEN(2,"快递员已揽件"),
    ON_WAY(3,"在途"),
    SENDING(4, "配送中"),
    DONE(5, "已完成"),
    OBSOLETED(9, "作废");

    @Getter
    private Integer code;
    @Getter
    private String desc;

    OrderTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDesc(Integer code){
        return Arrays.stream(values())
                .filter(e -> e.getCode().equals(code))
                .map(OrderTypeEnum::getDesc)
                .findFirst()
                .orElse("");
    }

}

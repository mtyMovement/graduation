package com.graduation.jaguar.core.common.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @Package com.sxx.sivir.core.common.enums
 * @Author: 尚星
 * @Date: 2019/4/18 10:48
 * @Copyright: 2018-2099  ShangXing - Sivir All rights reserved.
 * @Description: 地区类型枚举
 * 1-省， 2-市， 3-区
 */
public enum RegionTypeEnum {

    PROVINCE(1L,"省"),
    CITY(2L,"市"),
    COUNTRY(3L,"区");


    @Getter
    private Long code;
    @Getter
    private String desc;

    RegionTypeEnum(Long code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDesc(Long code){
        return Arrays.stream(values())
                .filter(e -> e.getCode().equals(code))
                .map(RegionTypeEnum::getDesc)
                .findFirst()
                .orElse("");
    }

}

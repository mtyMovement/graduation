package com.graduation.jaguar.core.common.util;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/13 0013
*/

import com.graduation.jaguar.core.common.enums.BaseEnum;

public class EnumUtil {
    public static <T extends BaseEnum> T getEnumByCode(Integer code, Class<T> enums){
        for (T each:enums.getEnumConstants()){
            if (each.getCode().equals(code)){
                return each;
            }
        }
        return null;
    }
    public static <T extends BaseEnum> T getEnumByMsg(String msg,Class<T> enums){
        for (T each:enums.getEnumConstants()){
            if (each.getDesc().equals(msg)){
                return each;
            }
        }
        return null;
    }

    public static <T extends BaseEnum> Integer getCodeByMsg(String msg,Class<T> enums){
        for (T each:enums.getEnumConstants()){
            if (each.getDesc().equals(msg)){
                return each.getCode();
            }
        }
        return null;
    }

    public static <T extends BaseEnum> String getMsgByCode(Integer code,Class<T> enums){
        for (T each:enums.getEnumConstants()){
            if (each.getCode().equals(code)){
                return each.getDesc();
            }
        }
        return null;
    }
}

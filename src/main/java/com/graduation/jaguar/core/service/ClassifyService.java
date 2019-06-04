package com.graduation.jaguar.core.service;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/18 0018
*/

import com.graduation.jaguar.core.dal.domain.Classify;

import java.util.List;

public interface ClassifyService {
    /**
     * 根据 name 获取对应的编码 code
     * @param classifyName
     * @return
     */
    String getClassifyCodeByName(String classifyName);

    void addNewClassify(Classify classify);

    List<Classify> queryAllClassify();

    String getClassifyNameByCode(String classifyCode);
}

package com.graduation.jaguar.web.controller;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/19 0019
*/

import com.graduation.jaguar.core.common.entity.APIResult;
import com.graduation.jaguar.core.dal.domain.Classify;
import com.graduation.jaguar.core.service.ClassifyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("register")
public class RegisterController {
    @Autowired
    ClassifyService classifyService;

    @RequestMapping("/initClassify")
    @ResponseBody
    public APIResult<List<Classify>> initClassify(){
        List<Classify> classifyList = classifyService.queryAllClassify();
        if(CollectionUtils.isEmpty(classifyList)){
            return APIResult.error("700","无类型信息，请联系管理员添加");
        }
        return APIResult.ok(classifyList);
    }
}

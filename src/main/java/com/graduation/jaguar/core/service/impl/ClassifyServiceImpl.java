package com.graduation.jaguar.core.service.impl;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/18 0018
*/

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.graduation.jaguar.core.dal.domain.Classify;
import com.graduation.jaguar.core.dal.manager.impl.ClassifyManagerImpl;
import com.graduation.jaguar.core.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassifyServiceImpl implements ClassifyService {
    @Autowired
    ClassifyManagerImpl classifyManager;

    @Override
    public String getClassifyCodeByName(String classifyName) {
        EntityWrapper<Classify> wrapper = new EntityWrapper<>();
        wrapper.eq("classify_name",classifyName);
        Classify classify = classifyManager.selectOne(wrapper);
        return classify.getClassifyCode();
    }

    @Override
    public void addNewClassify(Classify classify) {
        classifyManager.insert(classify);
    }

    @Override
    public List<Classify> queryAllClassify() {
        EntityWrapper<Classify> wrapper = new EntityWrapper<>();
        return classifyManager.selectList(wrapper);
    }

    @Override
    public String getClassifyNameByCode(String classifyCode) {
        EntityWrapper<Classify> wrapper = new EntityWrapper<>();
        wrapper.eq("classify_code",classifyCode);
        Classify classify = classifyManager.selectOne(wrapper);
        return classify.getClassifyName();
    }
}

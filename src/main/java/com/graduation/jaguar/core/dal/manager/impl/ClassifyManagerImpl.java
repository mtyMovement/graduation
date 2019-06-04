package com.graduation.jaguar.core.dal.manager.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.graduation.jaguar.core.common.enums.UserTypeEnum;
import com.graduation.jaguar.core.dal.domain.Classify;
import com.graduation.jaguar.core.dal.dao.ClassifyDao;
import com.graduation.jaguar.core.dal.domain.User;
import com.graduation.jaguar.core.dal.manager.ClassifyManager;
import com.graduation.jaguar.core.common.base.BaseManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClassifyManagerImpl extends BaseManagerImpl<ClassifyDao, Classify> implements ClassifyManager{
}

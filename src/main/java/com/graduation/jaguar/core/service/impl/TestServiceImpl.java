package com.graduation.jaguar.core.service.impl;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/4/22 0022
*/


import com.graduation.jaguar.core.common.util.QiniuService;
import com.graduation.jaguar.core.dal.domain.User;
import com.graduation.jaguar.core.dal.manager.impl.UserManagerImpl;
import com.graduation.jaguar.core.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private UserManagerImpl userManager;

    @Autowired
    private QiniuService qiniuService;

    @Override
    public void testSQLConnection() {

        User user = new User();
        user.setUserId(1);
        log.info(userManager.selectOne(user).toString());

    }

    @Override
    public void testQiniuConnection() throws Exception{
        //上传文件
        /*log.info("testQiniuConnection:开始上传");
        qiniuService.uploadFile("E:/work/video/动物世界.mp4");*/

        //获取上传地址
        log.info("testQiniuConnection:开始获取上传地址");
        String address = qiniuService.getFileResourceUrl("动物世界.mp4");
        log.info("testQiniuConnection:上传地址:{}",address);

        log.info("testQiniuConnection:开始获取文件url");
        qiniuService.getFileUrl("动物世界");

        log.info("testQiniuConnection:开始获取视频截图");
        qiniuService.qiNiuMediaPrtScreen("动物世界.mp4","jpg","854" , "478");
    }
}

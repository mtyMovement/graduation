package com.graduation.jaguar.core.service.impl;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/22 0022
*/

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.graduation.jaguar.core.common.VO.ClassifyInfoVO;
import com.graduation.jaguar.core.common.VO.UserInfoVO;
import com.graduation.jaguar.core.common.VO.VideoInfoVO;
import com.graduation.jaguar.core.common.entity.APIResult;
import com.graduation.jaguar.core.common.enums.UserTypeEnum;
import com.graduation.jaguar.core.common.enums.VideoAuditStatusEnum;
import com.graduation.jaguar.core.common.util.DateUtil;
import com.graduation.jaguar.core.dal.domain.Classify;
import com.graduation.jaguar.core.dal.domain.User;
import com.graduation.jaguar.core.dal.domain.Video;
import com.graduation.jaguar.core.dal.manager.impl.ClassifyManagerImpl;
import com.graduation.jaguar.core.dal.manager.impl.UserManagerImpl;
import com.graduation.jaguar.core.dal.manager.impl.VideoManagerImpl;
import com.graduation.jaguar.core.service.AdminService;
import com.graduation.jaguar.core.service.ClassifyService;
import com.graduation.jaguar.core.service.VideoService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.util.DateUtil.now;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    UserManagerImpl userManager;
    @Autowired
    ClassifyService classifyService;
    @Autowired
    ClassifyManagerImpl classifyManager;
    @Autowired
    VideoManagerImpl videoManager;
    @Autowired
    VideoServiceImpl videoService;

    @Override
    public APIResult<List<UserInfoVO>> queryUserInfo() {
        List<User> userList = userManager.queryUserInfo();
        if(CollectionUtils.isEmpty(userList)){
            return APIResult.error("800","暂无用户信息");
        }
        List<UserInfoVO> userInfoVOList = buildUserInfoVo(userList);
        return APIResult.ok(userInfoVOList);
    }

    @Override
    public APIResult<List<UserInfoVO>> queryUserInfo(String userName, String userType) {
        if(Objects.equals(userType, UserTypeEnum.NORMAL_USER.getDesc())){
            userType = "0";
        }else{
            userType = "5";
        }
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("user_isdelete", 0);
        if(StringUtils.isNotBlank(userName)){
            wrapper.eq("user_name", userName);
        }
        if(StringUtils.isNotBlank(userType)){
            wrapper.eq("user_type", userType);
        }
        List<User> userList = userManager.selectList(wrapper);
        if(CollectionUtils.isEmpty(userList)){
            return APIResult.error("800","暂无用户信息");
        }
        List<UserInfoVO> userInfoVOList = buildUserInfoVo(userList);
        return APIResult.ok(userInfoVOList);
    }

    @Override
    public APIResult deleteUser(Integer userId) {
        User user = userManager.selectById(userId);
        user.setUserIsdelete(1);
        user.setGmtModified(null);
        if(Objects.equals(userManager.updateById(user), 0) ){
            return APIResult.error("801", "删除失败");
        }

        return APIResult.ok();
    }

    @Override
    public APIResult<List<ClassifyInfoVO>> queryClassifyInfo() {
        EntityWrapper<Classify> wrapper = new EntityWrapper<>();
        wrapper.eq("is_valid", 0);
        List<Classify> classifyList = classifyManager.selectList(wrapper);
        if(CollectionUtils.isEmpty(classifyList)){
            return APIResult.error("900","暂无类别信息");
        }
        List<ClassifyInfoVO> classifyInfoVOList = classifyList.stream()
                .map(value ->{
                    ClassifyInfoVO classifyInfoVO = new ClassifyInfoVO();
                    BeanUtils.copyProperties(value, classifyInfoVO);
                    classifyInfoVO.setCreateTime(DateUtil.parseToString(
                            value.getCreateTime(), DateUtil.NORMAL_DATE));
                    return classifyInfoVO;
                })
                .collect(Collectors.toList());
        return APIResult.ok(classifyInfoVOList);
    }

    @Override
    public APIResult<List<ClassifyInfoVO>> queryClassifyInfo(String classifyName, String classifyCode) {
        EntityWrapper<Classify> wrapper = new EntityWrapper<>();
        wrapper.eq("is_valid", 0);
        if(StringUtils.isNotBlank(classifyName)){
            wrapper.eq("classify_name", classifyName);
        }
        if(StringUtils.isNotBlank(classifyCode)){
            wrapper.eq("classify_code", classifyCode);
        }
        List<Classify> classifyList = classifyManager.selectList(wrapper);
        if(CollectionUtils.isEmpty(classifyList)){
            return APIResult.error("910","未查到该信息");
        }
        List<ClassifyInfoVO> classifyInfoVOList = classifyList.stream()
                .map(value ->{
                    ClassifyInfoVO classifyInfoVO = new ClassifyInfoVO();
                    BeanUtils.copyProperties(value, classifyInfoVO);
                    classifyInfoVO.setCreateTime(DateUtil.parseToString(
                            value.getCreateTime(), DateUtil.NORMAL_DATE));
                    return classifyInfoVO;
                })
                .collect(Collectors.toList());
        return APIResult.ok(classifyInfoVOList);
    }

    @Override
    public APIResult addClassifyInfo(String classifyName, String classifyCode) {
        if(StringUtils.isBlank(classifyName) || StringUtils.isBlank(classifyCode)){
            return APIResult.error("920", "类型名称和编码都不得为空");
        }
        EntityWrapper<Classify> wrapper1 = new EntityWrapper<>();
        wrapper1.eq("classify_name", classifyName);
        EntityWrapper<Classify> wrapper2 = new EntityWrapper<>();
        wrapper2.eq("classify_code", classifyCode);
        if(CollectionUtils.isNotEmpty(classifyManager.selectList(wrapper1)) ||
                CollectionUtils.isNotEmpty(classifyManager.selectList(wrapper2))){
            return APIResult.error("922", "类型名称或编码不能重复");
        }
        Classify classify = new Classify();
        classify.setClassifyName(classifyName);
        classify.setClassifyCode(classifyCode);
        classify.setIsValid(0);
        if(Objects.equals(classifyManager.insert(classify), 1)){
            return APIResult.ok();
        }
        return APIResult.error("921", "新增类型失败");
    }

    @Override
    public APIResult<List<VideoInfoVO>> queryAuditVideoInfo() {
        EntityWrapper<Video> wrapper = new EntityWrapper<>();
        wrapper.eq("video_audit_status", VideoAuditStatusEnum.PENDING_AUDIT.getCode())
                .eq("is_deleted", 0);
        List<Video> videoList =  videoManager.selectList(wrapper);
        if(CollectionUtils.isNotEmpty(videoList)){
            List<VideoInfoVO> videoInfoVOList =  videoList.stream().map(value -> videoService.buildVideoInfoVO(value))
                    .collect(Collectors.toList());
            return APIResult.ok(videoInfoVOList);
        }else{
            return APIResult.error("701", "无待审核视频");
        }

    }

    @Override
    public APIResult auditVideo(Integer videoId, Integer auditResult) {
        Video video = videoManager.selectById(videoId);
        video.setVideoAuditTime(now());
        video.setModifyTime(null);
        if(auditResult == 0){
            video.setVideoAuditStatus(String.valueOf(VideoAuditStatusEnum.PASSED_AUDIT.getCode()));
        }else{
            video.setVideoAuditStatus(String.valueOf(VideoAuditStatusEnum.UNAUDITED.getCode()));
        }
        videoManager.updateById(video);
        return APIResult.ok();
    }

    public List<UserInfoVO> buildUserInfoVo(List<User> userList){
        List<UserInfoVO> userInfoVOListList = new ArrayList<>();
        userList.stream().forEach(value ->{
            UserInfoVO userInfoVO = new UserInfoVO();
            userInfoVO.setUserId(value.getUserId());
            userInfoVO.setUserNo(value.getUserNo());
            userInfoVO.setUserName(value.getUserName());
            userInfoVO.setCreateTime(DateUtil.parseToString(value.getGmtCreate(), DateUtil.NORMAL_DATE));
            if(Objects.equals(value.getUserType(), UserTypeEnum.NORMAL_USER.getCode())){
                userInfoVO.setUserType(UserTypeEnum.NORMAL_USER.getDesc());
            }else if(Objects.equals(value.getUserType(), UserTypeEnum.REGISTER_USER.getCode())){
                userInfoVO.setUserType(UserTypeEnum.REGISTER_USER.getDesc());
            }
            userInfoVO.setUserInterest("无");
            if(StringUtils.isNotBlank(value.getUserInterest())){
                List<String> interestList = Arrays.asList(value.getUserInterest().split(","));
                List<String> interestName = interestList.stream()
                        .map(interestValue -> classifyService.getClassifyNameByCode(interestValue))
                        .limit(3)
                        .collect(Collectors.toList());
                String interestString = "";
                for (String nameValue : interestName) {
                    interestString += nameValue;
                    interestString += " ";
                }
                if(interestList.size() > 3){
                    interestString += " 等";
                }
                userInfoVO.setUserInterest(interestString);
            }
            userInfoVOListList.add(userInfoVO);});
        return userInfoVOListList;
    }
}

package com.graduation.jaguar.core.dal.manager.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.graduation.jaguar.core.common.enums.OperateTypeEnum;
import com.graduation.jaguar.core.dal.dao.VideoDao;
import com.graduation.jaguar.core.dal.domain.Operation;
import com.graduation.jaguar.core.dal.dao.OperationDao;
import com.graduation.jaguar.core.dal.domain.Video;
import com.graduation.jaguar.core.dal.manager.OperationManager;
import com.graduation.jaguar.core.common.base.BaseManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class OperationManagerImpl extends BaseManagerImpl<OperationDao, Operation> implements OperationManager {

    @Autowired
    OperationDao operationDao;
    @Autowired
    VideoDao videoDao;

    @Override
    public Integer interestVideo(Integer userId, Integer videoId) {
        EntityWrapper<Operation> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId)
                .eq("diff_type_id", videoId)
                .eq("operate_type", OperateTypeEnum.OPERATE_VIDEO_INTEREST.getCode());
        List<Operation> operationList = operationDao.selectList(wrapper);
        //如果有多条记录则表示数据有问题
        if (operationList.size() > 1) {
            return -1;
        }
        //如果没有数据则新增记录
        else if (Objects.equals(operationList.size(), 0)) {
            //新增点赞记录
            Integer msg;
            Operation operation = new Operation();
            operation.setUserId(userId);
            operation.setDiffTypeId(videoId);
            operation.setOperateType(OperateTypeEnum.OPERATE_VIDEO_INTEREST.getCode());
            msg = operationDao.insert(operation);
            //新增则点赞数 +1
            if(msg == 1) {
                Video video = videoDao.selectById(videoId);
                Integer interestNum = video.getVideoInterested() + 1;
                video.setVideoInterested(interestNum);
                video.setModifyTime(null);
                if(videoDao.updateById(video) == 1){
                    return interestNum;
                }else{
                    return -1;
                }
            }else{
                return -1;
            }
        }
        //如果有一条记录说明点赞过，只需在点赞与取消点赞之间切换
        else{
            //修改点赞记录
            Integer msg;
            Operation operation = operationList.get(0);
            operation.setIsValid(operation.getIsValid() * -1);
            operation.setModifyTime(null);
            msg = operationDao.updateById(operation);
            if(msg == 1) {
                Video video = videoDao.selectById(videoId);
                Integer interestNum = video.getVideoInterested() - operation.getIsValid();
                video.setVideoInterested(interestNum);
                video.setModifyTime(null);
                if(videoDao.updateById(video) == 1){
                    return interestNum;
                }else{
                    return -1;
                }
            }else{
                return -1;
            }
        }
    }

    @Override
    public Integer shareVideo(Integer userId, Integer videoId) {
        return null;
    }

    @Override
    public Integer interestComment(Integer userId, Integer videoId) {
        return null;
    }
}

package com.graduation.jaguar.core.dal.manager;

import com.graduation.jaguar.core.common.enums.OperateTypeEnum;
import com.graduation.jaguar.core.dal.domain.Operation;
import com.graduation.jaguar.core.common.base.BaseManager;

public interface OperationManager extends BaseManager<Operation> {
    /**
     * 视频点赞，若已点赞则取消点赞，取消过再点赞不新增记录
     * @param userId
     * @param diffTypeId
     */
    Integer interestVideo(Integer userId, Integer diffTypeId);

    /**
     * 视频分享，若已分享则取消，只要该用户对此视频未分享都添加新的记录
     * @param userId
     * @param diffTypeId
     * @param remark
     * @return
     */
    Integer shareVideo(Integer userId, Integer diffTypeId, String remark);

    /**
     * 评论点赞，若已点赞则取消点赞，取消过再点赞不新增记录
     * @param userId
     * @param diffTypeId
     */
    Integer interestComment(Integer userId, Integer diffTypeId);

    /**
     * 评论视频，可对一个视频评论多次
     * @param userId
     * @param diffTypeId
     * @param remark
     * @return
     */
    Integer commentVideo(Integer userId, Integer diffTypeId, String remark);

    Integer subscribeUser(Integer userId, Integer diffTypeId);
}

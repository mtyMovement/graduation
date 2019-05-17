package com.graduation.jaguar.core.dal.manager;

import com.graduation.jaguar.core.dal.domain.Operation;
import com.graduation.jaguar.core.common.base.BaseManager;

public interface OperationManager extends BaseManager<Operation> {
    /**
     * 视频点赞，若已点赞则取消点赞，取消过再点赞不新增记录
     * @param userId
     * @param videoId
     */
    Integer interestVideo(Integer userId, Integer videoId);

    /**
     * 视频分享，若已分享则取消，只要该用户对此视频未分享都添加新的记录
     * @param userId
     * @param videoId
     */
    Integer shareVideo(Integer userId, Integer videoId);

    /**
     * 评论点赞，若已点赞则取消点赞，取消过再点赞不新增记录
     * @param userId
     * @param videoId
     */
    Integer interestComment(Integer userId, Integer videoId);
}

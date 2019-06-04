package com.graduation.jaguar.core.service;/*
 @author maoty
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/5/12 0012
*/

import com.baomidou.mybatisplus.plugins.Page;
import com.graduation.jaguar.core.common.DTO.VideoUploadDTO;
import com.graduation.jaguar.core.common.VO.VideoInfoVO;
import com.graduation.jaguar.core.common.entity.APIResult;
import com.graduation.jaguar.core.common.enums.OperateTypeEnum;
import com.graduation.jaguar.core.common.page.PageResult;

import java.util.List;
import java.util.Map;

public interface VideoService {
    /**
     * 上传视频, 同时生成视频截图
     * @param videoUploadDTO
     * @return
     * @throws Exception
     */
    APIResult uplaodVideo(VideoUploadDTO videoUploadDTO) throws Exception;

    /**
     * 根据视频 id 获取视频详细信息
     * @param videoId
     * @return
     */
    APIResult<VideoInfoVO> getVideoInfo(Integer videoId);

    /**
     * 获取当前最热的视频
     * @return
     */
    APIResult<List<VideoInfoVO>> getHottestVideoInfos(Integer limitNum);

    /**
     * 获关注类型的视频(返回八个，按时间排序)
     * @param interestTypes
     * @param limitNum
     * @return
     */
    APIResult<List<VideoInfoVO>> getInterestedVideoInfos(List<String> interestTypes, Integer limitNum);

    /**
     * 获关注用户发布的视频(返回八个，按时间排序)
     * @param userIdList
     * @param limitNum
     * @return
     */
    APIResult<List<VideoInfoVO>> getSubscribeVideoInfos(List<Integer> userIdList, Integer limitNum);

    /**
     * 获取当前最新的视频
     * @return
     */
    APIResult<List<VideoInfoVO>> getNewestVideoInfos(Integer limitNum);

    /**
     * 记录操作 点赞、转发、评论点赞等
     * @param userId
     * @param videoId
     * @return
     */
    APIResult<Integer> operateRecord(Integer userId, Integer videoId,
                                     OperateTypeEnum operateTypeEnum, String remark);

    APIResult<List<VideoInfoVO>> searchVideoByName(String videoName);

    /**
     * 加载评论
     * @param videoId
     * @return
     */
    APIResult<List<Map<String, String>>> queryCommentByVideoId(Integer videoId);

    /**
     * 获取该用户待审核视频
     * @param page
     * @param userId
     * @return
     */
    APIResult<Page<VideoInfoVO>> getAuditVideoBuUserId(Page page, Integer userId);

    /**
     * 获取该用户已发布视频
     * @param page
     * @param userId
     * @return
     */
    APIResult<Page<VideoInfoVO>> getPublicVideoBuUserId(Page page, Integer userId);

    /**
     * 获取该用户分享视频
     * @param page
     * @param userId
     * @return
     */
    APIResult<Page<VideoInfoVO>> getShareVideoBuUserId(Page page, Integer userId);

    /**
     * 获取该用户点赞视频
     * @param page
     * @param userId
     * @return
     */
    APIResult<Page<VideoInfoVO>> getInterestVideoBuUserId(Page page, Integer userId);

    /**
     * 删除视频
     * @param videoId
     * @return
     */
    APIResult personDeleteVideo(Integer videoId);
}

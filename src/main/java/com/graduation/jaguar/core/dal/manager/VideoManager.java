package com.graduation.jaguar.core.dal.manager;

import com.graduation.jaguar.core.dal.domain.Video;
import com.graduation.jaguar.core.common.base.BaseManager;

import java.util.List;

public interface VideoManager extends BaseManager<Video> {
    /**
     * 根据 videoId 获取 video 对象
     * @param videoId
     * @return
     */
    Video selectVideoByVideoId(Integer videoId);

    /**
     * 根据限制条数获取当前符合要求的视频，并正序或是倒叙
     * @param require 要求，及根据那个字符串进行查询
     * @param range  正序或是倒序输出 true 为正，false 为倒 ,默认倒序
     * @param limitNum 输出的数量 ,默认三条
     * @return
     */
    List<Video> getSatisfactoryVideo(String require, Boolean range, Integer limitNum);
}

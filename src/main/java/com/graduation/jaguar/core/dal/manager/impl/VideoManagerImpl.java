package com.graduation.jaguar.core.dal.manager.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.graduation.jaguar.core.common.enums.VideoAuditStatusEnum;
import com.graduation.jaguar.core.dal.domain.Video;
import com.graduation.jaguar.core.dal.dao.VideoDao;
import com.graduation.jaguar.core.dal.manager.VideoManager;
import com.graduation.jaguar.core.common.base.BaseManagerImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VideoManagerImpl extends BaseManagerImpl<VideoDao, Video> implements VideoManager{

    @Autowired
    VideoDao videoDao;

    @Override
    public Video selectVideoByVideoId(Integer videoId) {
        EntityWrapper<Video> wrapper = new EntityWrapper();
        wrapper.eq("video_id",videoId)
                .eq("is_deleted", 0)
                .eq("video_audit_status", VideoAuditStatusEnum.PASSED_AUDIT.getCode());
        List<Video> videoList = videoDao.selectList(wrapper);
        log.info("selectVideoByVideoId, videoList:{}",videoList);
        if(CollectionUtils.isNotEmpty(videoList)){
            return videoList.get(0);
        }else {
            return new Video();
        }
    }
}

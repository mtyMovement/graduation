package com.graduation.jaguar.core.dal.manager.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.graduation.jaguar.core.common.enums.VideoAuditStatusEnum;
import com.graduation.jaguar.core.dal.domain.Video;
import com.graduation.jaguar.core.dal.dao.VideoDao;
import com.graduation.jaguar.core.dal.manager.VideoManager;
import com.graduation.jaguar.core.common.base.BaseManagerImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class VideoManagerImpl extends BaseManagerImpl<VideoDao, Video> implements VideoManager{

    @Autowired
    VideoDao videoDao;

    @Override
    public Video selectVideoByVideoId(Integer videoId) {
        EntityWrapper<Video> wrapper = new EntityWrapper<>();
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

    @Override
    public List<Video> getSatisfactoryVideo(String require, Boolean range, Integer limitNum) {
        if(StringUtils.isEmpty(require)){
            return new ArrayList<>();
        }
        if(Objects.isNull(range)){
            range = false;
        }
        if(Objects.isNull(limitNum)){
            limitNum = 3;
        }
        EntityWrapper<Video> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", 0)
                .eq("video_audit_status", VideoAuditStatusEnum.PASSED_AUDIT.getCode())
                .orderBy(require, range)
                .last("limit " + limitNum);
        return videoDao.selectList(wrapper);
    }
}

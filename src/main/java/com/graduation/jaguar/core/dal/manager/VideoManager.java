package com.graduation.jaguar.core.dal.manager;

import com.graduation.jaguar.core.dal.domain.Video;
import com.graduation.jaguar.core.common.base.BaseManager;

public interface VideoManager extends BaseManager<Video> {
    Video selectVideoByVideoId(Integer videoId);
}

package com.thang.youtube.service.video;

import com.thang.youtube.model.dto.VideoForm;
import com.thang.youtube.model.entity.Video;
import com.thang.youtube.service.IGeneralService;

import java.util.List;

public interface IVideoService extends IGeneralService<Video> {
    Video saveVideo(VideoForm videoForm);

    List<Video> getVideoByUser_Id(Long userId);
    List<Video> findAllVideoByHastag(Long userId, Long hastagId);


}

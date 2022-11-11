package com.thang.youtube.service.watchedVideo;

import com.thang.youtube.model.dto.WatchedVideoDTO;
import com.thang.youtube.model.entity.WatchedVideo;
import com.thang.youtube.service.IGeneralService;

import java.util.List;

public interface IWatchedVideoService extends IGeneralService<WatchedVideo> {
    WatchedVideoDTO mappingWatchedVideoToWatchedVideoDTO(WatchedVideo watchedVideo);

    List<WatchedVideo> findWatchedVideoByUser_IdOrderByWatchedTime(Long userId);


    List<WatchedVideoDTO> mappingListWatchedVideoToListWatchedVideo(List<WatchedVideo> watchedVideos);
}

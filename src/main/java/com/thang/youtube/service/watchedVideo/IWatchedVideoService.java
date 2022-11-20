package com.thang.youtube.service.watchedVideo;

import com.thang.youtube.model.dto.WatchedVideoDTO;
import com.thang.youtube.model.entity.WatchedVideo;
import com.thang.youtube.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IWatchedVideoService extends IGeneralService<WatchedVideo> {
    WatchedVideoDTO mappingWatchedVideoToWatchedVideoDTO(WatchedVideo watchedVideo);

    List<WatchedVideo> findWatchedVideoByUser_IdOrderByWatchedTime(Long userId);

   Optional<WatchedVideo> findWatchedVideosByUser_IdAndVideo_Id(Long userId, Long videoId);

    List<WatchedVideoDTO> mappingListWatchedVideoToListWatchedVideo(List<WatchedVideo> watchedVideos);
}

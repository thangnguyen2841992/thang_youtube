package com.thang.youtube.service.watchedVideo;

import com.thang.youtube.model.dto.VideoResponse;
import com.thang.youtube.model.dto.WatchedVideoDTO;
import com.thang.youtube.model.entity.Video;
import com.thang.youtube.model.entity.WatchedVideo;
import com.thang.youtube.repository.IWatchedVideoRepository;
import com.thang.youtube.service.video.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WatchedVideoService implements IWatchedVideoService {
    @Autowired
    private IWatchedVideoRepository watchedVideoRepository;

    @Autowired
    private IVideoService videoService;

    @Override
    public Iterable<WatchedVideo> getAll() {
        return this.watchedVideoRepository.findAll();
    }

    @Override
    public Optional<WatchedVideo> getById(Long id) {
        return this.watchedVideoRepository.findById(id);
    }

    @Override
    public WatchedVideo save(WatchedVideo watchedVideo) {
        return this.watchedVideoRepository.save(watchedVideo);
    }

    @Override
    public void deleteById(Long id) {
        this.watchedVideoRepository.deleteById(id);
    }

    @Override
    public WatchedVideoDTO mappingWatchedVideoToWatchedVideoDTO(WatchedVideo watchedVideo) {
        WatchedVideoDTO watchedVideoDTO = new WatchedVideoDTO();
        watchedVideoDTO.setId(watchedVideo.getId());
        watchedVideoDTO.setUser(watchedVideo.getUser());
        VideoResponse videoResponse = this.videoService.mappingVideoToVideoResponse(watchedVideo.getVideo());
        watchedVideoDTO.setVideo(videoResponse);
        watchedVideoDTO.setWatchedTime(this.videoService.getDiffDays(watchedVideo.getWatchedTime(), new Date()));
        return watchedVideoDTO;
    }

    @Override
    public List<WatchedVideo> findWatchedVideoByUser_IdOrderByWatchedTime(Long userId) {
        return this.watchedVideoRepository.findWatchedVideoByUser_IdOrderByWatchedTime(userId);
    }

    @Override
    public List<WatchedVideoDTO> mappingListWatchedVideoToListWatchedVideo(List<WatchedVideo> watchedVideos) {
        List<WatchedVideoDTO> watchedVideoList = new ArrayList<>();
        for (int i = 0; i < watchedVideos.size(); i++) {
            watchedVideoList.add(mappingWatchedVideoToWatchedVideoDTO(watchedVideos.get(i)));
        }
        return watchedVideoList;
    }
}

package com.thang.youtube.service.video;

import com.thang.youtube.model.dto.VideoForm;
import com.thang.youtube.model.dto.VideoResponse;
import com.thang.youtube.model.entity.Video;
import com.thang.youtube.service.IGeneralService;

import java.util.Date;
import java.util.List;

public interface IVideoService extends IGeneralService<Video> {
    Video saveVideo(VideoForm videoForm);

    List<Video> getVideoByUser_Id(Long userId);
    List<Video> findAllVideoByHastag(Long userId, Long hastagId);

    String getDiffDays(Date time1, Date time2);

    VideoResponse mappingVideoToVideoResponse(Video video);

    List<VideoResponse> mappingListVideoToListVideoResponse(List<Video> videos);

    List<Video> getVideoOtherUser(Long userId);

    List<Video> findAllVideoOtherUserAndOtherCurrentVideo(Long userId, Long videoId);

    String getUrlById(Long videoId);

    List<Video> findVideosById(Long id);


}

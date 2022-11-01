package com.thang.youtube.service.video;

import com.thang.youtube.model.dto.VideoForm;
import com.thang.youtube.model.dto.VideoResponse;
import com.thang.youtube.model.entity.Hastag;
import com.thang.youtube.model.entity.PlayList;
import com.thang.youtube.model.entity.User;
import com.thang.youtube.model.entity.Video;
import com.thang.youtube.repository.IVideoRepository;
import com.thang.youtube.service.hastag.IHastagService;
import com.thang.youtube.service.playList.IPlayListService;
import com.thang.youtube.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VideoService implements IVideoService {
    @Autowired
    private IVideoRepository videoRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IHastagService hastagService;
    @Autowired
    private IPlayListService playListService;

    @Override
    public Iterable<Video> getAll() {
        return this.videoRepository.findAll();
    }

    @Override
    public Optional<Video> getById(Long id) {
        return this.videoRepository.findById(id);
    }

    @Override
    public Video save(Video video) {

        return this.videoRepository.save(video);
    }

    @Override
    public void deleteById(Long id) {
        this.videoRepository.deleteById(id);
    }

    @Override
    public Video saveVideo(VideoForm videoForm) {
        Video newVideo = new Video();
        newVideo.setName(videoForm.getName());
        newVideo.setUrl(videoForm.getUrl());
        newVideo.setDateCreated(new Date());
        Optional<User> userOptional = this.userService.getById(videoForm.getUserId());
        newVideo.setUser(userOptional.get());
        Optional<Hastag> hastagOptional = this.hastagService.getById(videoForm.getHastagId());
        newVideo.setHastag(hastagOptional.get());
        return newVideo;
    }

    @Override
    public List<Video> getVideoByUser_Id(Long userId) {
        return this.videoRepository.getVideoByUser_Id(userId);
    }

    @Override
    public List<Video> findAllVideoByHastag(Long userId, Long hastagId) {
        return this.videoRepository.findAllVideoByHastag(userId, hastagId);
    }

    @Override
    public String getDiffDays(Date time1, Date time2) {
        long timeDifferenceMilliseconds = (time2.getTime() - time1.getTime());
        long diffSeconds = timeDifferenceMilliseconds / 1000;
        long diffMinutes = timeDifferenceMilliseconds / (60 * 1000);
        long diffHours = timeDifferenceMilliseconds / (60 * 60 * 1000);
        long diffDays = timeDifferenceMilliseconds / (60 * 60 * 1000 * 24);
        long diffWeeks = timeDifferenceMilliseconds / (60 * 60 * 1000 * 24 * 7);
        long diffMonths = (long) (timeDifferenceMilliseconds / (60 * 60 * 1000 * 24 * 30.41666666));
        long diffYears = timeDifferenceMilliseconds / ((long) 60 * 60 * 1000 * 24 * 365);

        if (diffSeconds < 1) {
            return "vừa xong";
        } else if (diffMinutes < 1) {
            return diffSeconds + " giây";
        } else if (diffHours < 1) {
            return diffMinutes + " phút";
        } else if (diffDays < 1) {
            return diffHours + " giờ";
        } else if (diffWeeks < 1) {
            return diffDays + " ngày";
        } else if (diffMonths < 1) {
            return diffWeeks + " tuần";
        } else if (diffYears < 1) {
            return diffMonths + " tháng";
        } else {
            return diffYears + " năm";
        }
    }

    @Override
    public VideoResponse mappingVideoToVideoResponse(Video video) {
        String name = video.getName();
        String firstLetter = name.substring(0, 1);
        String remainingLetters = name.substring(1, name.length());
        firstLetter = firstLetter.toUpperCase();
        name = firstLetter + remainingLetters;
        VideoResponse videoResponse = new VideoResponse();
        videoResponse.setId(video.getId());
        videoResponse.setUrl(video.getUrl());
        videoResponse.setName(name);
        videoResponse.setPlayList(video.getPlayList());
        videoResponse.setHastag(video.getHastag());
        videoResponse.setDateCreated(getDiffDays(video.getDateCreated(), new Date()));
        videoResponse.setUser(video.getUser());
        return videoResponse;
    }

    @Override
    public List<VideoResponse> mappingListVideoToListVideoResponse(List<Video> videos) {
        List<VideoResponse> videoResponseList = new ArrayList<>();
        for (Video video : videos) {
            videoResponseList.add(mappingVideoToVideoResponse(video));
        }
        return videoResponseList;
    }
}

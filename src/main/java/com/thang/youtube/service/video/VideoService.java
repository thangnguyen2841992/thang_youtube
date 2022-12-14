package com.thang.youtube.service.video;

import com.thang.youtube.model.dto.CommentDTO;
import com.thang.youtube.model.dto.VideoForm;
import com.thang.youtube.model.dto.VideoResponse;
import com.thang.youtube.model.entity.*;
import com.thang.youtube.repository.IVideoRepository;
import com.thang.youtube.service.comment.ICommentService;
import com.thang.youtube.service.dislike.IDisLikeService;
import com.thang.youtube.service.hastag.IHastagService;
import com.thang.youtube.service.like.ILikeService;
import com.thang.youtube.service.playList.IPlayListService;
import com.thang.youtube.service.subscriber.ISubscriberService;
import com.thang.youtube.service.user.IUserService;
import com.thang.youtube.service.view.IViewService;
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
    @Autowired
    private ISubscriberService subscriberService;
    @Autowired
    private ILikeService likeService;
    @Autowired
    private IDisLikeService disLikeService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IViewService viewService;


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
        newVideo.setDescription(videoForm.getDescription());
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
        return this.videoRepository.getVideoByUser_IdOrderByDateCreatedDesc(userId);
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
            return "v???a xong";
        } else if (diffMinutes < 1) {
            return diffSeconds + " gi??y";
        } else if (diffHours < 1) {
            return diffMinutes + " ph??t";
        } else if (diffDays < 1) {
            return diffHours + " gi???";
        } else if (diffWeeks < 1) {
            return diffDays + " ng??y";
        } else if (diffMonths < 1) {
            return diffWeeks + " tu???n";
        } else if (diffYears < 1) {
            return diffMonths + " th??ng";
        } else {
            return diffYears + " n??m";
        }
    }

    @Override
    public String getDiffDays1(Date time1, Date time2) {
        long timeDifferenceMilliseconds = (time2.getTime() - time1.getTime());
        long diffDays = timeDifferenceMilliseconds / (60 * 60 * 1000 * 24);
        if (time1.getYear() == time2.getYear() && time1.getMonth() == time2.getMonth() && (time2.getDate() - time1.getDate() < 1)) {
            return "H??m nay";
        }else if (time1.getYear() == time2.getYear() && time1.getMonth() == time2.getMonth() && (time2.getDate() - time1.getDate() == 1)) {
            return "H??m qua";
        }else {
            int month = time1.getMonth() + 1;
            return (time1.getDate() + "/" + month);
        }
    }

    @Override
    public VideoResponse mappingVideoToVideoResponse(Video video) {
        String name = video.getName();
        String firstLetter = name.substring(0, 1);
        String remainingLetters = name.substring(1, name.length());
        firstLetter = firstLetter.toUpperCase();
        name = firstLetter + remainingLetters;
        List<Subscriber> subscribers = this.subscriberService.findSubscribersByUser_Id(video.getUser().getId());
        int totalSubscriber = subscribers.size();
        VideoResponse videoResponse = new VideoResponse();
        videoResponse.setId(video.getId());
        videoResponse.setUrl(video.getUrl());
        videoResponse.setName(name);
        videoResponse.setDescription(video.getDescription());
        videoResponse.setPlayList(video.getPlayList());
        videoResponse.setHastag(video.getHastag());
        videoResponse.setDateCreated(getDiffDays(video.getDateCreated(), new Date()));
        videoResponse.setUser(video.getUser());
        videoResponse.setTotalSubscriber(totalSubscriber);
        List<Like> likes = this.likeService.findLikeByVideo_Id(video.getId());
        videoResponse.setTotalLike(likes.size());
        List<DisLike> disLikes = this.disLikeService.findDisLikeByVideo_Id(video.getId());
        videoResponse.setTotalDisLike(disLikes.size());
        List<Comment> comments = this.commentService.findCommentByVideo_Id(video.getId());
        int totalReply = 0;
        List<CommentDTO> commentDTOList = this.commentService.mappingListCommentToListCommentDTO(comments);
        for (int i = 0; i < commentDTOList.size(); i++) {
            totalReply += commentDTOList.get(i).getTotalReply();
        }
        videoResponse.setTotalComment(comments.size() + totalReply);
        List<View> views = this.viewService.findViewByVideo_Id(video.getId());
        videoResponse.setTotalView(views.size());
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

    @Override
    public List<Video> getVideoOtherUser(Long userId) {
        return this.videoRepository.getVideoOtherUser(userId);
    }

    @Override
    public List<Video> findAllVideoOtherUserAndOtherCurrentVideo(Long userId, Long videoId) {
        return this.videoRepository.findAllVideoOtherUserAndOtherCurrentVideo(userId, videoId);
    }

    @Override
    public String getUrlById(Long videoId) {
        return this.videoRepository.getUrlById(videoId);
    }

    @Override
    public List<Video> findVideosById(Long id) {
        return this.videoRepository.findVideosById(id);
    }

    @Override
    public List<Video> findVideosByNameContaining(String name) {
        String name1 = "%" + name + "%";
        List<Video> videos = this.videoRepository.findVideosByNameContaining(name1);
        return videos;
    }

    @Override
    public void deleteListVideo(List<Long> videoId) {
        for (int i = 0; i < videoId.size(); i++) {
            this.videoRepository.deleteById(videoId.get(i));
        }
    }


}

package com.thang.youtube.controller;

import com.thang.youtube.model.dto.Message;
import com.thang.youtube.model.dto.WatchedVideoDTO;
import com.thang.youtube.model.entity.User;
import com.thang.youtube.model.entity.Video;
import com.thang.youtube.model.entity.WatchedVideo;
import com.thang.youtube.service.user.IUserService;
import com.thang.youtube.service.video.IVideoService;
import com.thang.youtube.service.watchedVideo.IWatchedVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/watchedVideos")
public class WatchedVideoRestController {
    @Autowired
    private IWatchedVideoService watchedVideoService;
    @Autowired
    private IVideoService iVideoService;
    @Autowired
    private IUserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllWatchedVideoOfUser(@PathVariable Long userId) {
        List<WatchedVideo> watchedVideos = this.watchedVideoService.findWatchedVideoByUser_IdOrderByWatchedTime(userId);
        List<WatchedVideoDTO> watchedVideoDTOList = this.watchedVideoService.mappingListWatchedVideoToListWatchedVideo(watchedVideos);
        return new ResponseEntity<>(watchedVideoDTOList, HttpStatus.OK);
    }

    @PostMapping("/video/{videoId}/user/{userId}")
    public ResponseEntity<?> addWatchedVideoOfUser(@PathVariable Long videoId, @PathVariable Long userId) {
        Optional<Video> video = this.iVideoService.getById(videoId);
        if (!video.isPresent()) {
            return new ResponseEntity<>(new Message("Video không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        Optional<User> user = this.userService.getById(userId);
        if (!user.isPresent()) {
            return new ResponseEntity<>(new Message("Người dùng không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        WatchedVideo watchedVideo = new WatchedVideo();
        watchedVideo.setVideo(video.get());
        watchedVideo.setUser(user.get());
        watchedVideo.setWatchedTime(new Date());
        this.watchedVideoService.save(watchedVideo);
        return new ResponseEntity<>(watchedVideo, HttpStatus.CREATED);
    }
}

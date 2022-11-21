package com.thang.youtube.controller;

import com.thang.youtube.model.dto.Check;
import com.thang.youtube.model.dto.Message;
import com.thang.youtube.model.entity.*;
import com.thang.youtube.service.dislike.IDisLikeService;
import com.thang.youtube.service.like.ILikeService;
import com.thang.youtube.service.likedVideo.ILikedVideoService;
import com.thang.youtube.service.user.IUserService;
import com.thang.youtube.service.video.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/likes")
@CrossOrigin("*")
public class LikeRestController {
    @Autowired
    private IVideoService videoService;
    @Autowired
    private ILikeService likeService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IDisLikeService disLikeService;
    @Autowired
    private ILikedVideoService likedVideoService;
    @PostMapping("/video/{videoId}/user/{userId}")
    public ResponseEntity<?> addLikeVideo(@PathVariable Long videoId, @PathVariable Long userId) {
        Optional<Video> videoOptional = this.videoService.getById(videoId);
        if (!videoOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Video không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        Optional<User> userOptional  = this.userService.getById(userId);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Người dùng không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        Optional<Like> likeOptional = this.likeService.findLikeByVideo_IdAndUser_Id(videoId, userId);
        Optional<DisLike> disLikeOptional = this.disLikeService.findDisLikeByVideo_IdAndUSer_Id(videoId, userId);
        Optional<LikedVideo> likedVideoOptional = this.likedVideoService.findLikedVideoByUser_IdAndVideo_Id(userId, videoId);
        Like newLike = new Like();
        if (!likeOptional.isPresent()) {
            newLike.setVideo(videoOptional.get());
            newLike.setUser(userOptional.get());
            disLikeOptional.ifPresent(disLike -> this.disLikeService.deleteById(disLike.getId()));
            this.likeService.save(newLike);
            LikedVideo likedVideo = this.likedVideoService.save(this.likedVideoService.saveLikeVideo(userId, videoId));
            this.likedVideoService.save(likedVideo);
            return new ResponseEntity<>(newLike, HttpStatus.CREATED);
        }
        likeOptional.ifPresent(like -> this.likeService.deleteById(like.getId()));
        likedVideoOptional.ifPresent(liked -> this.likedVideoService.deleteById(liked.getId()));
        return new ResponseEntity<>(new Message("Tạo Like không thành công"), HttpStatus.NO_CONTENT);
    }
    @PostMapping("/checkLike/video/{videoId}/user/{userId}")
    public ResponseEntity<Check> checkLikeVideo(@PathVariable Long videoId, @PathVariable Long userId) {
        Check check = new Check();
        Optional<Like> likeOptional = this.likeService.findLikeByVideo_IdAndUser_Id(videoId, userId);
        if (!likeOptional.isPresent()) {
            check.setIsSubscriber(false);
        }
        else {
            check.setIsSubscriber(true);
        }
        return new ResponseEntity<>(check, HttpStatus.OK);
    }
}

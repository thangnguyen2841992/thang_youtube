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
@CrossOrigin("*")
@RequestMapping("/dislikes")
public class DisLikeRestController {
    @Autowired
    private IDisLikeService disLikeService;
    @Autowired
    private IVideoService videoService;
    @Autowired
    private ILikeService likeService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ILikedVideoService likedVideoService;
    @PostMapping("/video/{videoId}/user/{userId}")
    public ResponseEntity<?> addDisLikeVideo(@PathVariable Long videoId, @PathVariable Long userId) {
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
        Optional<LikedVideo> likedVideo = this.likedVideoService.findLikedVideoByUser_IdAndVideo_Id(userId, videoId);
        DisLike newDisLike = new DisLike();
        if (!disLikeOptional.isPresent()) {
            newDisLike.setVideo(videoOptional.get());
            newDisLike.setUser(userOptional.get());
            likeOptional.ifPresent(like -> this.likeService.deleteById(like.getId()));
            likedVideo.ifPresent(likedVideo1 -> this.likedVideoService.deleteById(likedVideo1.getId()));
            this.disLikeService.save(newDisLike);
            return new ResponseEntity<>(newDisLike, HttpStatus.CREATED);
        } else {
            this.disLikeService.deleteById(disLikeOptional.get().getId());
        }
        return new ResponseEntity<>(new Message("Tạo Like không thành công"), HttpStatus.NO_CONTENT);
    }
    @PostMapping("/checkDisLike/video/{videoId}/user/{userId}")
    public ResponseEntity<Check> checkDisLikeVideo(@PathVariable Long videoId, @PathVariable Long userId) {
        Check check = new Check();
        Optional<DisLike> disLikeOptional = this.disLikeService.findDisLikeByVideo_IdAndUSer_Id(videoId, userId);
        if (!disLikeOptional.isPresent()) {
            check.setIsSubscriber(false);
        }
        else {
            check.setIsSubscriber(true);
        }
        return new ResponseEntity<>(check, HttpStatus.OK);
    }
}

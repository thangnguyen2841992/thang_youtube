package com.thang.youtube.controller;

import com.thang.youtube.model.entity.LikedVideo;
import com.thang.youtube.service.likedVideo.ILikedVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/likedVideos")
public class LikedVideoRestController {
    @Autowired
    private ILikedVideoService likedVideoService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllLikedVideoOfUser(@PathVariable Long userId) {
        return new ResponseEntity<>(this.likedVideoService.findLikedVideoByUser_Id(userId), HttpStatus.OK);
    }
}

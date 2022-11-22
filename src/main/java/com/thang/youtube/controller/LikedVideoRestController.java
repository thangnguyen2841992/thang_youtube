package com.thang.youtube.controller;

import com.thang.youtube.model.dto.LikedVideoDTO;
import com.thang.youtube.service.likedVideo.ILikedVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/likedVideos")
public class LikedVideoRestController {
    @Autowired
    private ILikedVideoService likedVideoService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllLikedVideoOfUser(@PathVariable Long userId, @RequestParam(value = "limit") int limit) {
        List<LikedVideoDTO> likedVideoDTOS = this.likedVideoService.findLikedVideoOfUser(userId, limit);
        return new ResponseEntity<>(likedVideoDTOS, HttpStatus.OK);
    }
}

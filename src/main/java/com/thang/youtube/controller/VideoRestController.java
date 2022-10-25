package com.thang.youtube.controller;

import com.thang.youtube.model.dto.VideoForm;
import com.thang.youtube.service.video.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/videos")
public class VideoRestController {
    @Autowired
    private IVideoService videoService;

    @PostMapping
    public ResponseEntity<?> createNewVideo(@RequestBody VideoForm videoForm) {
        return new ResponseEntity<>(this.videoService.saveVideo(videoForm), HttpStatus.CREATED);
    }
}

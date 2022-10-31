package com.thang.youtube.controller;

import com.thang.youtube.model.dto.Message;
import com.thang.youtube.model.dto.VideoForm;
import com.thang.youtube.model.entity.Video;
import com.thang.youtube.service.video.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/videos")
public class VideoRestController {
    @Autowired
    private IVideoService videoService;

    @GetMapping
    public ResponseEntity<?> getAllVideo() {
        List<Video> videos = (List<Video>) this.videoService.getAll();
        if (videos.size() == 0) {
            return new ResponseEntity<>(new Message("Không có video nào!"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createNewVideo(@RequestBody VideoForm videoForm) {
        Video newVideo = this.videoService.saveVideo(videoForm);
        return new ResponseEntity<>(this.videoService.save(newVideo), HttpStatus.CREATED);
    }
}

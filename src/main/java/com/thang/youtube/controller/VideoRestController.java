package com.thang.youtube.controller;

import com.thang.youtube.model.dto.Message;
import com.thang.youtube.model.dto.VideoForm;
import com.thang.youtube.model.dto.VideoResponse;
import com.thang.youtube.model.entity.Hastag;
import com.thang.youtube.model.entity.Video;
import com.thang.youtube.service.video.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/videos")
public class VideoRestController {
    @Autowired
    private IVideoService videoService;

    @GetMapping("/all/user/{userId}")
    public ResponseEntity<?> getAllVideo(@PathVariable Long userId) {
        List<Video> videos =  this.videoService.getVideoOtherUser(userId);
        if (videos.size() == 0) {
            return new ResponseEntity<>(new Message("Không có video nào!"), HttpStatus.NO_CONTENT);
        }
        List<VideoResponse> videoResponseList = this.videoService.mappingListVideoToListVideoResponse(videos);
        return new ResponseEntity<>(videoResponseList, HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllVideoByUser(@PathVariable Long userId) {
        List<Video> videos = this.videoService.getVideoByUser_Id(userId);
        if (videos.size() == 0) {
            return new ResponseEntity<>(new Message("Không có video nào!"), HttpStatus.NO_CONTENT);
        }
        List<VideoResponse> videoResponseList = this.videoService.mappingListVideoToListVideoResponse(videos);
        return new ResponseEntity<>(videoResponseList, HttpStatus.OK);
    }
    @GetMapping("/video/{videoId}")
    public ResponseEntity<?> getVideoById(@PathVariable Long videoId) {
        Optional<Video> videoOptional = this.videoService.getById(videoId);
        if (!videoOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Video không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        Video video = videoOptional.get();
        VideoResponse videoResponse = this.videoService.mappingVideoToVideoResponse(video);
        return new ResponseEntity<>(videoResponse, HttpStatus.OK);
    }
    @GetMapping("/test/video/{videoId}")
    public ResponseEntity<?> getVideoById1(@PathVariable Long videoId) {
        List<Video> videos = this.videoService.findVideosById(videoId);
        List<VideoResponse> videoResponseList = this.videoService.mappingListVideoToListVideoResponse(videos);
        return new ResponseEntity<>(videoResponseList, HttpStatus.OK);
    }

        @GetMapping("/hastag/{hastagId}/user/{userId}")
    public ResponseEntity<?> getAllVideoByHastag(@PathVariable Long hastagId, @PathVariable Long userId) {
        List<Video> videos = this.videoService.findAllVideoByHastag(userId, hastagId);
        if (videos.size() == 0) {
            return new ResponseEntity<>(new Message("Không có video nào!"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(this.videoService.mappingListVideoToListVideoResponse(videos), HttpStatus.OK);
    }
    @GetMapping("/user/{userId}/video/{videoId}")
    public ResponseEntity<?> getAllVideoOtherUserOtherVideo( @PathVariable Long userId, @PathVariable Long videoId) {
        List<Video> videos = this.videoService.findAllVideoOtherUserAndOtherCurrentVideo(userId, videoId);
        if (videos.size() == 0) {
            return new ResponseEntity<>(new Message("Không có video nào!"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(this.videoService.mappingListVideoToListVideoResponse(videos), HttpStatus.OK);
    }
    @GetMapping("/url/video/{videoId}")
    public ResponseEntity<?> getUrlByVideoId(@PathVariable Long videoId) {
        String url = this.videoService.getUrlById(videoId);
        return new ResponseEntity<>(url, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createNewVideo(@RequestBody VideoForm videoForm) {
        Video newVideo = this.videoService.saveVideo(videoForm);
        return new ResponseEntity<>(this.videoService.save(newVideo), HttpStatus.CREATED);
    }
    @GetMapping("/searchByName")
    public ResponseEntity<?> findVideoByNameContaining(@RequestParam(name = "q") String name) {
        List<Video> videos = this.videoService.findVideosByNameContaining(name);
        List<VideoResponse> videoResponseList = this.videoService.mappingListVideoToListVideoResponse(videos);
        return new ResponseEntity<>(videoResponseList, HttpStatus.OK);
    }
    @PostMapping("/deleteVideo")
    public ResponseEntity<?> deleteListVideo(@RequestBody List<Long> videoIds) {
        this.videoService.deleteListVideo(videoIds);
        return new ResponseEntity<>(new Message("Xóa thành công!"), HttpStatus.OK);
    }
}

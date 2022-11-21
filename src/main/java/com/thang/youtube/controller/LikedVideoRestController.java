package com.thang.youtube.controller;

import com.thang.youtube.service.likedVideo.ILikedVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/likedVideos")
public class LikedVideoRestController {
    @Autowired
    private ILikedVideoService likedVideoService;
}

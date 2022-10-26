package com.thang.youtube.controller;

import com.thang.youtube.service.playList.IPlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/playLists")
@CrossOrigin("*")
public class PlayListRestController {
    @Autowired
    private IPlayListService playListService;
}

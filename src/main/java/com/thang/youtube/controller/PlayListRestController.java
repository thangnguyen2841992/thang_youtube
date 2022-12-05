package com.thang.youtube.controller;

import com.thang.youtube.model.dto.PlayListForm;
import com.thang.youtube.model.entity.PlayList;
import com.thang.youtube.model.entity.User;
import com.thang.youtube.service.playList.IPlayListService;
import com.thang.youtube.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playLists")
@CrossOrigin("*")
public class PlayListRestController {
    @Autowired
    private IPlayListService playListService;
    @Autowired
    private IUserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllPlayListOfUser(@PathVariable Long userId) {
        List<PlayList> playLists = this.playListService.findPlayListByUser_Id(userId);
        return new ResponseEntity<>(playLists, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createNewPlayList(@RequestBody PlayListForm playListForm) {
        User user = this.userService.getById(playListForm.getUserId()).get();
        PlayList newPlayList = new PlayList();
        newPlayList.setUser(user);
        newPlayList.setName(playListForm.getName());
        this.playListService.save(newPlayList);
        return new ResponseEntity<>(newPlayList, HttpStatus.CREATED);
    }
}

package com.thang.youtube.controller;

import com.thang.youtube.model.dto.ViewForm;
import com.thang.youtube.model.entity.User;
import com.thang.youtube.model.entity.Video;
import com.thang.youtube.model.entity.View;
import com.thang.youtube.service.user.IUserService;
import com.thang.youtube.service.user.UserService;
import com.thang.youtube.service.video.IVideoService;
import com.thang.youtube.service.view.IViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/views")
public class ViewRestController {
    @Autowired
    private IViewService viewService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IVideoService videoService;

    @PostMapping
    public ResponseEntity<?> addNewWatched(@RequestBody ViewForm viewForm) {
        Video video = this.videoService.getById(viewForm.getVideoId()).get();
        User currentUser = this.userService.getById(viewForm.getCurrentUserID()).get();
        View newView = new View();
        if (currentUser.getId() != video.getUser().getId()) {
            newView.setUser(currentUser);
            newView.setVideo(video);
            this.viewService.save(newView);
        }
        return new ResponseEntity<>(newView, HttpStatus.CREATED);
    }
//    @GetMapping("/getWebBrowser")
//    public ResponseEntity<?> getWebBrowser() {
//        return new ResponseEntity<>(this.viewService.getWebBrowse(), HttpStatus.OK);
//    }
}

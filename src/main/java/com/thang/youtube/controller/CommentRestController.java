package com.thang.youtube.controller;

import com.thang.youtube.model.dto.CommentDTO;
import com.thang.youtube.model.dto.CommentForm;
import com.thang.youtube.model.entity.Comment;
import com.thang.youtube.service.comment.ICommentService;
import com.thang.youtube.service.user.IUserService;
import com.thang.youtube.service.video.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin("*")
public class CommentRestController {
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IVideoService videoService;

    @PostMapping
    public ResponseEntity<?> createNewComment(@RequestBody CommentForm commentForm) {
        Comment newComment = this.commentService.saveComment(commentForm);

        return new ResponseEntity<>(this.commentService.save(newComment), HttpStatus.CREATED);
    }
    @GetMapping("/video/{videoId}")
    public ResponseEntity<?> getAllCommentOfVideo(@PathVariable Long videoId) {
        List<Comment> comments = this.commentService.findCommentByVideo_Id(videoId);
        return new ResponseEntity<>(this.commentService.mappingListCommentToListCommentDTO(comments), HttpStatus.OK);
    }
    @GetMapping("/showListCommentOrderByTotalLike/video/{videoId}")
    public ResponseEntity<?> showListCommentOrderByTotalLike(@PathVariable Long videoId) {
        List<Comment> comments = this.commentService.findCommentByVideo_Id(videoId);
        List<CommentDTO> commentDTOList = this.commentService.showListCommentOrderByTotalLike(comments);
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }
}

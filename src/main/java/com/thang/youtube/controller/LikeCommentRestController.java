package com.thang.youtube.controller;

import com.thang.youtube.model.dto.Check;
import com.thang.youtube.model.dto.Message;
import com.thang.youtube.model.entity.*;
import com.thang.youtube.service.comment.ICommentService;
import com.thang.youtube.service.dislikeComment.IDisLikeCommentService;
import com.thang.youtube.service.likeComment.ILikeCommentService;
import com.thang.youtube.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/likeComments")
public class LikeCommentRestController {
    @Autowired
    private ICommentService commentService;
    @Autowired
    private ILikeCommentService likeCommentService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IDisLikeCommentService disLikeCommentService;

    @PostMapping("/addLikeComment/comment/{commentId}/user/{userId}")
    public ResponseEntity<?> addNewLikeComment(@PathVariable Long commentId, @PathVariable Long userId) {
        Optional<Comment> commentOptional = this.commentService.getById(commentId);
        if (!commentOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Comment không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        Optional<User> userOptional = this.userService.getById(userId);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Người dùng không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        Optional<LikeComment> likeCommentOptional = this.likeCommentService.findLikeCommentByComment_IdAndUser_Id(commentId, userId);
        Optional<DisLikeComment> disLikeCommentOptional = this.disLikeCommentService.findDisLikeCommentByComment_IdAndUser_Id(commentId, userId);
        LikeComment newLikeComment = new LikeComment();
        if (!likeCommentOptional.isPresent()) {
            newLikeComment.setComment(commentOptional.get());
            newLikeComment.setUser(userOptional.get());
            if (disLikeCommentOptional.isPresent()) {
                this.disLikeCommentService.deleteById(disLikeCommentOptional.get().getId());
            }
            this.likeCommentService.save(newLikeComment);

        } else {
            this.likeCommentService.deleteById(likeCommentOptional.get().getId());
        }
        return new ResponseEntity<>(newLikeComment, HttpStatus.CREATED);
    }
    @PostMapping("/checkLike/comment/{commentId}/user/{userId}")
    public ResponseEntity<Check> checkLikeComment(@PathVariable Long commentId, @PathVariable Long userId) {
        Check check = new Check();
        Optional<LikeComment> likeOptional = this.likeCommentService.findLikeCommentByComment_IdAndUser_Id(commentId, userId);
        if (!likeOptional.isPresent()) {
            check.setIsSubscriber(false);
        }
        else {
            check.setIsSubscriber(true);
        }
        return new ResponseEntity<>(check, HttpStatus.OK);
    }
}

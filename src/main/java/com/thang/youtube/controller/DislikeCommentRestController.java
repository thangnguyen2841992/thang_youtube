package com.thang.youtube.controller;

import com.thang.youtube.model.dto.Message;
import com.thang.youtube.model.entity.Comment;
import com.thang.youtube.model.entity.DisLikeComment;
import com.thang.youtube.model.entity.LikeComment;
import com.thang.youtube.model.entity.User;
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
@RequestMapping("/dislikeComments")
@CrossOrigin("*")
public class DislikeCommentRestController {
    @Autowired
    private ICommentService commentService;
    @Autowired
    private ILikeCommentService likeCommentService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IDisLikeCommentService disLikeCommentService;
    @PostMapping("/addDislikeComment/comment/{commentId}/user/{userId}")
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
        DisLikeComment newDislikeComment = new DisLikeComment();
        if (!disLikeCommentOptional.isPresent()) {
            newDislikeComment.setComment(commentOptional.get());
            newDislikeComment.setUser(userOptional.get());
            if (likeCommentOptional.isPresent()) {
                this.likeCommentService.deleteById(likeCommentOptional.get().getId());
            }
            this.disLikeCommentService.save(newDislikeComment);

        } else {
            this.disLikeCommentService.deleteById(disLikeCommentOptional.get().getId());
        }
        return new ResponseEntity<>(newDislikeComment, HttpStatus.CREATED);
    }
}

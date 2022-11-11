package com.thang.youtube.controller;

import com.thang.youtube.model.dto.Message;
import com.thang.youtube.model.entity.*;
import com.thang.youtube.service.dislikeReply.IDislikeReplyService;
import com.thang.youtube.service.likeReply.ILikeReplyService;
import com.thang.youtube.service.reply.IReplyService;
import com.thang.youtube.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/likeReplies")
public class LikeReplyRestController {
    @Autowired
    private ILikeReplyService likeReplyService;
    @Autowired
    private IReplyService replyService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IDislikeReplyService dislikeReplyService;

    @PostMapping("/addLikeReply/reply/{replyId}/user/{userId}")
    public ResponseEntity<?> addNewLikeReply(@PathVariable Long replyId, @PathVariable Long userId) {
        Optional<ReplyComment> replyCommentOptional = this.replyService.getById(replyId);
        if (!replyCommentOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Phản hồi không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        Optional<User> userOptional = this.userService.getById(userId);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(new Message("Người dùng không tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        Optional<LikeReply> likeReply = this.likeReplyService.findLikeReplyByReplyComment_IdAndUser_Id(replyId, userId);
        Optional<DislikeReply> dislikeReply = this.dislikeReplyService.findDislikeReplyByReplyComment_IdAndUser_Id(replyId, userId);
        LikeReply newLikeReply = new LikeReply();
        if (!likeReply.isPresent()) {
            newLikeReply.setReplyComment(replyCommentOptional.get());
            newLikeReply.setUser(userOptional.get());
            if (dislikeReply.isPresent()) {
                this.dislikeReplyService.deleteById(dislikeReply.get().getId());
            }
            this.likeReplyService.save(newLikeReply);

        } else {
            this.likeReplyService.deleteById(likeReply.get().getId());
        }
        return new ResponseEntity<>(newLikeReply, HttpStatus.CREATED);
    }
}

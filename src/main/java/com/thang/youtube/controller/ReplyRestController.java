package com.thang.youtube.controller;

import com.thang.youtube.model.dto.ReplyForm;
import com.thang.youtube.model.entity.ReplyComment;
import com.thang.youtube.service.reply.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/replies")
public class ReplyRestController {
    @Autowired
    private IReplyService replyService;

    @PostMapping
    public ResponseEntity<?> addNewReply(@RequestBody ReplyForm replyForm) {
        ReplyComment newReply = this.replyService.saveReply(replyForm);
        this.replyService.save(newReply);
        return new ResponseEntity<>(newReply, HttpStatus.CREATED);
    }


}

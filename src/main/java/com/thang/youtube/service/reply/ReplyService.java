package com.thang.youtube.service.reply;

import com.thang.youtube.model.dto.CommentDTO;
import com.thang.youtube.model.dto.ReplyDTO;
import com.thang.youtube.model.dto.ReplyForm;
import com.thang.youtube.model.entity.*;
import com.thang.youtube.repository.IReplyCommentRepository;
import com.thang.youtube.service.comment.ICommentService;
import com.thang.youtube.service.user.IUserService;
import com.thang.youtube.service.video.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReplyService implements IReplyService {
    @Autowired
    private IReplyCommentRepository replyCommentRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICommentService commentService;

    @Autowired
    private IVideoService videoService;

    @Override
    public Iterable<ReplyComment> getAll() {
        return this.replyCommentRepository.findAll();
    }

    @Override
    public Optional<ReplyComment> getById(Long id) {
        return this.replyCommentRepository.findById(id);
    }

    @Override
    public ReplyComment save(ReplyComment replyComment) {
        return this.replyCommentRepository.save(replyComment);
    }

    @Override
    public void deleteById(Long id) {
        this.replyCommentRepository.deleteById(id);
    }

    @Override
    public ReplyComment saveReply(ReplyForm replyForm) {
        ReplyComment newReply = new ReplyComment();
        User user = this.userService.getById(replyForm.getUserId()).get();
        Comment comment = this.commentService.getById(replyForm.getCommentId()).get();
        newReply.setComment(comment);
        newReply.setUser(user);
        newReply.setContent(replyForm.getContent());
        newReply.setDateCreated(new Date());
        return newReply;
    }

    @Override
    public ReplyDTO mappingReplyToReplyDTO(ReplyComment replyComment) {
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setId(replyComment.getId());
        replyDTO.setContent(replyComment.getContent());
        replyDTO.setDateCreated(this.videoService.getDiffDays(replyComment.getDateCreated(), new Date()));
        replyDTO.setUser(replyComment.getUser());
        replyDTO.setComment(replyComment.getComment());
        return replyDTO;
    }

    @Override
    public List<ReplyDTO> mappingListReplyToListReplyDTO(List<ReplyComment> replyComments) {
        List<ReplyDTO> replyDTOList = new ArrayList<>();
        for (int i = 0; i < replyComments.size(); i++) {
            replyDTOList.add(mappingReplyToReplyDTO(replyComments.get(i)));
        }
        return replyDTOList;
    }

    @Override
    public List<ReplyComment> findReplyCommentsByComment_Id(Long commentId) {
        return this.replyCommentRepository.findReplyCommentsByComment_Id(commentId);
    }
}

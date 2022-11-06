package com.thang.youtube.service.comment;

import com.thang.youtube.model.dto.CommentDTO;
import com.thang.youtube.model.dto.CommentForm;
import com.thang.youtube.model.entity.*;
import com.thang.youtube.repository.ICommentRepository;
import com.thang.youtube.service.dislikeComment.IDisLikeCommentService;
import com.thang.youtube.service.likeComment.ILikeCommentService;
import com.thang.youtube.service.user.IUserService;
import com.thang.youtube.service.video.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IVideoService videoService;
    @Autowired
    private ILikeCommentService likeCommentService;
    @Autowired
    private IDisLikeCommentService disLikeCommentService;


    @Override
    public Iterable<Comment> getAll() {
        return this.commentRepository.findAll();
    }

    @Override
    public Optional<Comment> getById(Long id) {
        return this.commentRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return this.commentRepository.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        this.commentRepository.deleteById(id);
    }

    @Override
    public Comment saveComment(CommentForm commentForm) {
        Comment newComment = new Comment();
        User user = this.userService.getById(commentForm.getUserId()).get();
        Video video = this.videoService.getById(commentForm.getVideoId()).get();
        newComment.setVideo(video);
        newComment.setUser(user);
        newComment.setContent(commentForm.getContent());
        newComment.setDateCreated(new Date());
        return newComment;
    }

    @Override
    public List<Comment> findCommentByVideo_Id(Long videoId) {
        return this.commentRepository.findCommentByVideo_Id(videoId);
    }

    @Override
    public CommentDTO mappingCommentToCommentDTO(Comment comment) {
        List<LikeComment> likeCommentList = this.likeCommentService.findLikeCommentByComment_Id(comment.getId());
        List<DisLikeComment> disLikeCommentList = this.disLikeCommentService.findDisLikeCommentByComment_Id(comment.getId());
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setDateCreated(this.videoService.getDiffDays(comment.getDateCreated(), new Date()));
        commentDTO.setUser(comment.getUser());
        commentDTO.setVideo(comment.getVideo());
        commentDTO.setTotalLike(likeCommentList.size());
        commentDTO.setTotalDislike(disLikeCommentList.size());
        return commentDTO;
    }

    @Override
    public List<CommentDTO> mappingListCommentToListCommentDTO(List<Comment> comments) {
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (int i = 0; i < comments.size(); i++) {
            commentDTOList.add(this.mappingCommentToCommentDTO(comments.get(i)));
        }
        return commentDTOList;
    }

}

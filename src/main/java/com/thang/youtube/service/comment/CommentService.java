package com.thang.youtube.service.comment;

import com.thang.youtube.model.dto.CommentForm;
import com.thang.youtube.model.entity.Comment;
import com.thang.youtube.model.entity.User;
import com.thang.youtube.model.entity.Video;
import com.thang.youtube.repository.ICommentRepository;
import com.thang.youtube.service.user.IUserService;
import com.thang.youtube.service.video.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IVideoService videoService;

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
}

package com.thang.youtube.service.likeComment;

import com.thang.youtube.model.dto.Check;
import com.thang.youtube.model.entity.LikeComment;
import com.thang.youtube.repository.ILikeCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeCommentService implements ILikeCommentService{
    @Autowired
    private ILikeCommentRepository likeCommentRepository;

    @Override
    public Iterable<LikeComment> getAll() {
        return this.likeCommentRepository.findAll();
    }

    @Override
    public Optional<LikeComment> getById(Long id) {
        return this.likeCommentRepository.findById(id);
    }

    @Override
    public LikeComment save(LikeComment likeComment) {
        return this.likeCommentRepository.save(likeComment);
    }

    @Override
    public void deleteById(Long id) {
        this.likeCommentRepository.deleteById(id);
    }

    @Override
    public Optional<LikeComment> findLikeCommentByComment_IdAndUser_Id(Long commentId, Long userId) {
        return this.likeCommentRepository.findLikeCommentByComment_IdAndUser_Id(commentId, userId);
    }

    @Override
    public List<LikeComment> findLikeCommentByComment_Id(Long commentId) {
        return this.likeCommentRepository.findLikeCommentByComment_Id(commentId);
    }

    @Override
    public Check checkLikeComment(Long commentId, Long userId) {
        Check check = new Check();
        Optional<LikeComment> likeOptional = this.findLikeCommentByComment_IdAndUser_Id(commentId, userId);
        if (!likeOptional.isPresent()) {
            check.setIsSubscriber(false);
        }
        else {
            check.setIsSubscriber(true);
        }
        return check;
    }
}

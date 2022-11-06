package com.thang.youtube.service.dislikeComment;

import com.thang.youtube.model.entity.DisLikeComment;
import com.thang.youtube.repository.IDisLikeCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisLikeCommentService implements IDisLikeCommentService{
    @Autowired
    private IDisLikeCommentRepository dishLikeCommentRepository;

    @Override
    public Iterable<DisLikeComment> getAll() {
        return this.dishLikeCommentRepository.findAll();
    }

    @Override
    public Optional<DisLikeComment> getById(Long id) {
        return this.dishLikeCommentRepository.findById(id);
    }

    @Override
    public DisLikeComment save(DisLikeComment disLikeComment) {
        return this.dishLikeCommentRepository.save(disLikeComment);
    }

    @Override
    public void deleteById(Long id) {
        this.dishLikeCommentRepository.deleteById(id);
    }

    @Override
    public Optional<DisLikeComment> findDisLikeCommentByComment_IdAndUser_Id(Long commentId, Long userId) {
        return this.dishLikeCommentRepository.findDisLikeCommentByComment_IdAndUser_Id(commentId, userId);
    }

    @Override
    public List<DisLikeComment> findDisLikeCommentByComment_Id(Long commentId) {
        return this.dishLikeCommentRepository.findDisLikeCommentByComment_Id(commentId);
    }
}

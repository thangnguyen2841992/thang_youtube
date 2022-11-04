package com.thang.youtube.service.dislikeComment;

import com.thang.youtube.model.entity.DisLikeComment;
import com.thang.youtube.repository.IDishLikeCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DisLikeCommentService implements IDisLikeCommentService{
    @Autowired
    private IDishLikeCommentRepository dishLikeCommentRepository;

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
}

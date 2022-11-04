package com.thang.youtube.service.like;

import com.thang.youtube.model.entity.Like;
import com.thang.youtube.repository.ILikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService implements ILikeService {
    @Autowired
    private ILikeRepository likeRepository;

    @Override
    public Iterable<Like> getAll() {
        return this.likeRepository.findAll();
    }

    @Override
    public Optional<Like> getById(Long id) {
        return this.likeRepository.findById(id);
    }

    @Override
    public Like save(Like like) {
        return this.likeRepository.save(like);
    }

    @Override
    public void deleteById(Long id) {
        this.likeRepository.deleteById(id);
    }

    @Override
    public Optional<Like> findLikeByVideo_IdAndUser_Id(Long videoId, Long userId) {
        return this.likeRepository.findLikeByVideo_IdAndUser_Id(videoId, userId);
    }

    @Override
    public List<Like> findLikeByVideo_Id(Long videoId) {
        return this.likeRepository.findLikeByVideo_Id(videoId);
    }
}

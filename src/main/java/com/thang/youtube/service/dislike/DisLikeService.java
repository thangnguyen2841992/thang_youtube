package com.thang.youtube.service.dislike;

import com.thang.youtube.model.entity.DisLike;
import com.thang.youtube.repository.IDisLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisLikeService implements IDisLikeService{
    @Autowired
    private IDisLikeRepository disLikeRepository;

    @Override
    public Iterable<DisLike> getAll() {
        return this.disLikeRepository.findAll();
    }

    @Override
    public Optional<DisLike> getById(Long id) {
        return this.disLikeRepository.findById(id);
    }

    @Override
    public DisLike save(DisLike disLike) {
        return this.disLikeRepository.save(disLike);
    }

    @Override
    public void deleteById(Long id) {
        this.disLikeRepository.deleteById(id);
    }

    @Override
    public Optional<DisLike> findDisLikeByVideo_IdAndUSer_Id(Long videoId, Long userId) {
        return this.disLikeRepository.findDisLikeByVideo_IdAndUser_Id(videoId,userId);
    }

    @Override
    public List<DisLike> findDisLikeByVideo_Id(Long videoId) {
        return this.disLikeRepository.findDisLikeByVideo_Id(videoId);
    }
}

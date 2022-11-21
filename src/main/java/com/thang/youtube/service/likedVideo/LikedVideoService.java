package com.thang.youtube.service.likedVideo;

import com.thang.youtube.model.entity.LikedVideo;
import com.thang.youtube.repository.ILikedVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikedVideoService implements ILikedVideoService{

    @Autowired
    private ILikedVideoRepository likedVideoRepository;

    @Override
    public Iterable<LikedVideo> getAll() {
        return this.likedVideoRepository.findAll();
    }

    @Override
    public Optional<LikedVideo> getById(Long id) {
        return this.likedVideoRepository.findById(id);
    }

    @Override
    public LikedVideo save(LikedVideo likedVideo) {
        return this.likedVideoRepository.save(likedVideo);
    }

    @Override
    public void deleteById(Long id) {
        this.likedVideoRepository.deleteById(id);
    }
}

package com.thang.youtube.service.video;

import com.thang.youtube.model.entity.Video;
import com.thang.youtube.repository.IVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VideoService implements IVideoService{
    @Autowired
    private IVideoRepository videoRepository;
    @Override
    public Iterable<Video> getAll() {
        return this.videoRepository.findAll();
    }

    @Override
    public Optional<Video> getById(Long id) {
        return this.videoRepository.findById(id);
    }

    @Override
    public Video save(Video video) {
        return this.videoRepository.save(video);
    }

    @Override
    public void deleteById(Long id) {
        this.videoRepository.deleteById(id);
    }
}

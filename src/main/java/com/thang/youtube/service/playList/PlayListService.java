package com.thang.youtube.service.playList;

import com.thang.youtube.model.entity.PlayList;
import com.thang.youtube.repository.IPlayListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayListService implements IPlayListService {
    @Autowired
    private IPlayListRepository playListRepository;
    @Override
    public Iterable<PlayList> getAll() {
        return this.playListRepository.findAll();
    }

    @Override
    public Optional<PlayList> getById(Long id) {
        return this.playListRepository.findById(id);
    }

    @Override
    public PlayList save(PlayList playList) {
        return this.playListRepository.save(playList);
    }

    @Override
    public void deleteById(Long id) {
        this.playListRepository.deleteById(id);
    }
}

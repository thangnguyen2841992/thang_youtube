package com.thang.youtube.service.video;

import com.thang.youtube.model.dto.VideoForm;
import com.thang.youtube.model.entity.Hastag;
import com.thang.youtube.model.entity.PlayList;
import com.thang.youtube.model.entity.User;
import com.thang.youtube.model.entity.Video;
import com.thang.youtube.repository.IVideoRepository;
import com.thang.youtube.service.hastag.IHastagService;
import com.thang.youtube.service.playList.IPlayListService;
import com.thang.youtube.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class VideoService implements IVideoService{
    @Autowired
    private IVideoRepository videoRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IHastagService hastagService;
    @Autowired
    private IPlayListService playListService;
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

    @Override
    public Video saveVideo(VideoForm videoForm) {
        Video newVideo = new Video();
        newVideo.setUrl(videoForm.getUrl());
        newVideo.setCurrentDate(new Date());
        Optional<User> userOptional = this.userService.getById(videoForm.getUserId());
        if (userOptional.isPresent()) {
            newVideo.setUser(userOptional.get());
        }
        Optional<PlayList> playListOptional = this.playListService.getById(videoForm.getPlayListId());
        if (playListOptional.isPresent()) {
            newVideo.setPlayList(playListOptional.get());
        }
        Optional<Hastag> hastagOptional = this.hastagService.getById(videoForm.getHastagId());
        if (hastagOptional.isPresent()) {
            newVideo.setHastag(hastagOptional.get());
        }
        return newVideo;
    }
}

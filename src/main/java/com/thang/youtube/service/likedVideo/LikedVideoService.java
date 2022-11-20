package com.thang.youtube.service.likedVideo;

import com.thang.youtube.model.dto.LikedVideoDTO;
import com.thang.youtube.model.entity.LikedVideo;
import com.thang.youtube.model.entity.User;
import com.thang.youtube.model.entity.Video;
import com.thang.youtube.repository.ILikedVideoRepository;
import com.thang.youtube.service.user.IUserService;
import com.thang.youtube.service.video.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LikedVideoService implements ILikedVideoService{
    @Autowired
    private ILikedVideoRepository likedVideoRepository;
    @Autowired
    private IVideoService videoService;
    @Autowired
    private IUserService userService;

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

    @Override
    public List<LikedVideoDTO> findLikedVideoByUser_Id(Long userId) {
        List<LikedVideo> likedVideos = this.likedVideoRepository.findLikedVideoByUser_Id(userId);
        List<LikedVideoDTO> likedVideoDTOS = new ArrayList<>();
        for (int i = 0; i < likedVideos.size(); i++) {
            likedVideoDTOS.add(mappingLikedVideoToLikedVideoDTO(likedVideos.get(i)));
        }
       return likedVideoDTOS;
    }

    @Override
    public LikedVideoDTO mappingLikedVideoToLikedVideoDTO(LikedVideo likedVideo) {
        LikedVideoDTO likedVideoDTO = new LikedVideoDTO();
        likedVideoDTO.setId(likedVideo.getId());
        likedVideoDTO.setLikedVideoTime(this.videoService.getDiffDays(likedVideo.getLikedTime(), new Date()));
        likedVideoDTO.setVideo(this.videoService.mappingVideoToVideoResponse(likedVideo.getVideo()));
        likedVideoDTO.setUser(likedVideo.getUser());
        return likedVideoDTO;
    }

    @Override
    public LikedVideo saveLikeVideo(Long userId, Long videoId) {
        User user = this.userService.getById(userId).get();
        Video video = this.videoService.getById(videoId).get();
        LikedVideo newLikeVideo = new LikedVideo();
        newLikeVideo.setLikedTime(new Date());
        newLikeVideo.setUser(user);
        newLikeVideo.setVideo(video);
        return newLikeVideo;
    }

    @Override
    public Optional<LikedVideo> findLikedVideoByUser_IdAndVideo_Id(Long userId, Long videoId) {
        return this.likedVideoRepository.findLikedVideosByUser_IdAndVideo_Id(userId, videoId);
    }
}

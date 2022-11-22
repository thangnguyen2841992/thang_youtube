package com.thang.youtube.service.likedVideo;

import com.thang.youtube.model.dto.LikedVideoDTO;
import com.thang.youtube.model.entity.LikedVideo;
import com.thang.youtube.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ILikedVideoService extends IGeneralService<LikedVideo> {

    List<LikedVideoDTO> findLikedVideoOfUser(Long userId, int limit);
    LikedVideoDTO mappingLikedVideoToLikedVideoDTO(LikedVideo likedVideo);
    LikedVideo saveLikeVideo(Long userId, Long videoId);
    Optional<LikedVideo> findLikedVideoByUser_IdAndVideo_Id(Long userId, Long videoId);

}

package com.thang.youtube.service.like;

import com.thang.youtube.model.entity.Like;
import com.thang.youtube.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ILikeService extends IGeneralService<Like> {
    Optional<Like> findLikeByVideo_IdAndUser_Id(Long videoId, Long userId);

    List<Like> findLikeByVideo_Id(Long videoId);


}

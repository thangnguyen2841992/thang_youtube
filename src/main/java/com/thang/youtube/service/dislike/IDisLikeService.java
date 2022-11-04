package com.thang.youtube.service.dislike;

import com.thang.youtube.model.entity.DisLike;
import com.thang.youtube.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IDisLikeService extends IGeneralService<DisLike> {
    Optional<DisLike> findDisLikeByVideo_IdAndUSer_Id(Long videoId, Long userId);

    List<DisLike> findDisLikeByVideo_Id(Long videoId);


}

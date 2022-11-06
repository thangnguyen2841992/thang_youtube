package com.thang.youtube.service.dislikeComment;

import com.thang.youtube.model.entity.DisLikeComment;
import com.thang.youtube.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IDisLikeCommentService extends IGeneralService<DisLikeComment> {
    Optional<DisLikeComment> findDisLikeCommentByComment_IdAndUser_Id(Long commentId, Long userId);

    List<DisLikeComment> findDisLikeCommentByComment_Id(Long commentId);


}

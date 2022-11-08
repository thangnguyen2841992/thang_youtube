package com.thang.youtube.service.likeComment;

import com.thang.youtube.model.dto.Check;
import com.thang.youtube.model.entity.LikeComment;
import com.thang.youtube.service.IGeneralService;

import java.util.List;
import java.util.Optional;


public interface ILikeCommentService extends IGeneralService<LikeComment> {
    Optional<LikeComment> findLikeCommentByComment_IdAndUser_Id(Long commentId, Long userId);

    List<LikeComment> findLikeCommentByComment_Id(Long commentId);

    Check checkLikeComment(Long commentId, Long userId);

}

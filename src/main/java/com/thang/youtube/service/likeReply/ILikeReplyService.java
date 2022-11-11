package com.thang.youtube.service.likeReply;

import com.thang.youtube.model.entity.LikeReply;
import com.thang.youtube.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ILikeReplyService extends IGeneralService<LikeReply> {
    Optional<LikeReply> findLikeReplyByReplyComment_IdAndUser_Id(Long replyId, Long userId);

    List<LikeReply> findLikeReplyByReplyComment_Id(Long replyId);


}

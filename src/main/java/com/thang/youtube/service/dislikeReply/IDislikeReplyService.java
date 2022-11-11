package com.thang.youtube.service.dislikeReply;

import com.thang.youtube.model.entity.DislikeReply;
import com.thang.youtube.service.IGeneralService;

import java.util.Optional;

public interface IDislikeReplyService extends IGeneralService<DislikeReply> {
    Optional<DislikeReply> findDislikeReplyByReplyComment_IdAndUser_Id(Long replyId, Long userId);

}

package com.thang.youtube.service.reply;

import com.thang.youtube.model.dto.CommentDTO;
import com.thang.youtube.model.dto.CommentForm;
import com.thang.youtube.model.dto.ReplyDTO;
import com.thang.youtube.model.dto.ReplyForm;
import com.thang.youtube.model.entity.Comment;
import com.thang.youtube.model.entity.ReplyComment;
import com.thang.youtube.service.IGeneralService;

import java.util.List;

public interface IReplyService extends IGeneralService<ReplyComment> {

    ReplyComment saveReply(ReplyForm replyForm);

    ReplyDTO mappingReplyToReplyDTO(ReplyComment replyComment);

    List<ReplyDTO> mappingListReplyToListReplyDTO(List<ReplyComment> replyComments);

    List<ReplyComment> findReplyCommentsByComment_Id(Long commentId);




}

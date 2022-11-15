package com.thang.youtube.service.comment;

import com.thang.youtube.model.dto.CommentDTO;
import com.thang.youtube.model.dto.CommentForm;
import com.thang.youtube.model.entity.Comment;
import com.thang.youtube.service.IGeneralService;

import java.util.List;

public interface ICommentService extends IGeneralService<Comment> {

    Comment saveComment(CommentForm commentForm);

    List<Comment> findCommentByVideo_Id(Long videoId);

    CommentDTO mappingCommentToCommentDTO(Comment comment);

    List<CommentDTO> mappingListCommentToListCommentDTO(List<Comment> comments);

    List<CommentDTO> showListCommentOrderByTotalLike(List<Comment> comments);
}

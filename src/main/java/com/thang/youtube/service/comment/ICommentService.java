package com.thang.youtube.service.comment;

import com.thang.youtube.model.dto.CommentForm;
import com.thang.youtube.model.entity.Comment;
import com.thang.youtube.service.IGeneralService;

public interface ICommentService extends IGeneralService<Comment> {

    Comment saveComment(CommentForm commentForm);
}

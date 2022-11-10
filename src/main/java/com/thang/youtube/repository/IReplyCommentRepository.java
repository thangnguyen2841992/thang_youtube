package com.thang.youtube.repository;

import com.thang.youtube.model.entity.ReplyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReplyCommentRepository extends JpaRepository<ReplyComment, Long> {
    List<ReplyComment> findReplyCommentsByComment_IdOrderByDateCreatedDesc(Long commentId);
}

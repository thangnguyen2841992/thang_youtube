package com.thang.youtube.repository;

import com.thang.youtube.model.entity.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILikeCommentRepository extends JpaRepository<LikeComment, Long> {
    Optional<LikeComment> findLikeCommentByComment_IdAndUser_Id(Long commentId, Long userId);

    List<LikeComment> findLikeCommentByComment_Id(Long commentId);
}

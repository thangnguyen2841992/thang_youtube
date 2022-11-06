package com.thang.youtube.repository;

import com.thang.youtube.model.entity.DisLikeComment;
import com.thang.youtube.model.entity.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDisLikeCommentRepository extends JpaRepository<DisLikeComment, Long> {
    Optional<DisLikeComment> findDisLikeCommentByComment_IdAndUser_Id(Long commentId, Long userId);

    List<DisLikeComment> findDisLikeCommentByComment_Id(Long commentId);


}

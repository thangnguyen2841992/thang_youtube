package com.thang.youtube.repository;

import com.thang.youtube.model.entity.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikeCommentRepository extends JpaRepository<LikeComment, Long> {
}

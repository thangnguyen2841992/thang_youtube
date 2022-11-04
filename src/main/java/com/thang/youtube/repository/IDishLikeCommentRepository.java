package com.thang.youtube.repository;

import com.thang.youtube.model.entity.DisLikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDishLikeCommentRepository extends JpaRepository<DisLikeComment, Long> {
}

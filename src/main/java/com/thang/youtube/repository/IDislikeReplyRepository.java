package com.thang.youtube.repository;

import com.thang.youtube.model.entity.DislikeReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDislikeReplyRepository extends JpaRepository<DislikeReply, Long> {
    Optional<DislikeReply> findDislikeReplyByReplyComment_IdAndUser_Id(Long replyId, Long userId);
}

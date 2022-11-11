package com.thang.youtube.repository;

import com.thang.youtube.model.entity.LikeReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface ILikeReplyRepository extends JpaRepository<LikeReply, Long> {
    Optional<LikeReply> findLikeReplyByReplyComment_IdAndUser_Id(Long replyId, Long userId);

    List<LikeReply> findLikeReplyByReplyComment_Id(Long replyId);
}

package com.thang.youtube.repository;

import com.thang.youtube.model.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findLikeByVideo_IdAndUser_Id(Long videoId, Long userId);

    List<Like> findLikeByVideo_Id(Long videoId);
}

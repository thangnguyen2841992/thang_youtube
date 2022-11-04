package com.thang.youtube.repository;

import com.thang.youtube.model.entity.DisLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDisLikeRepository extends JpaRepository<DisLike, Long> {
    Optional<DisLike> findDisLikeByVideo_IdAndUser_Id(Long videoId, Long userId);

    List<DisLike>  findDisLikeByVideo_Id(Long videoId);
}

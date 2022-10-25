package com.thang.youtube.repository;

import com.thang.youtube.model.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVideoRepository extends JpaRepository<Video, Long> {
}

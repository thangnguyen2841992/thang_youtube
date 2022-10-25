package com.thang.youtube.repository;

import com.thang.youtube.model.entity.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayListRepository extends JpaRepository<PlayList, Long> {
}

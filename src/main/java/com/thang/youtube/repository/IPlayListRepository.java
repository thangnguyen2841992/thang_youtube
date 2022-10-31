package com.thang.youtube.repository;

import com.thang.youtube.model.entity.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPlayListRepository extends JpaRepository<PlayList, Long> {
    List<PlayList> findPlayListByUser_Id(Long userId);
}

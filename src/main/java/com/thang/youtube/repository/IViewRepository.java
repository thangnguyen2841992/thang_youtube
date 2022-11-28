package com.thang.youtube.repository;

import com.thang.youtube.model.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IViewRepository extends JpaRepository<View, Long> {
}

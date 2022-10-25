package com.thang.youtube.repository;

import com.thang.youtube.model.entity.Hastag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHastagRepository extends JpaRepository<Hastag, Long> {
}

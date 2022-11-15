package com.thang.youtube.repository;

import com.thang.youtube.model.entity.Subscriber;
import com.thang.youtube.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISubscriberRepository extends JpaRepository<Subscriber, Long> {
    List<Subscriber> findSubscribersByUser_Id(Long userId);
    Optional<Subscriber> findSubscribersByUser_IdAndMember_Id(Long userId, Long memberId);

    List<Subscriber> findSubscribersByMember_Id(Long memberId);

}

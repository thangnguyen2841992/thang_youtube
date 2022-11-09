package com.thang.youtube.service.subscriber;

import com.thang.youtube.model.entity.Subscriber;
import com.thang.youtube.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ISubscriberService extends IGeneralService<Subscriber> {

    List<Subscriber> findSubscribersByUser_Id(Long userId);

    Optional<Subscriber> findSubscribersByUser_IdAndMember_Id(Long userId, Long memberId);

}

package com.thang.youtube.service.subscriber;

import com.thang.youtube.model.entity.Subscriber;
import com.thang.youtube.service.IGeneralService;

import java.util.List;

public interface ISubscriberService extends IGeneralService<Subscriber> {

    List<Subscriber> findSubscribersByUser_Id(Long userId);

}

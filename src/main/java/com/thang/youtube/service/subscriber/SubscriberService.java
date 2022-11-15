package com.thang.youtube.service.subscriber;

import com.thang.youtube.model.entity.Subscriber;
import com.thang.youtube.repository.ISubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriberService implements ISubscriberService{
    @Autowired
    private ISubscriberRepository subscriberRepository;

    @Override
    public Iterable<Subscriber> getAll() {
        return this.subscriberRepository.findAll();
    }

    @Override
    public Optional<Subscriber> getById(Long id) {
        return this.subscriberRepository.findById(id);
    }

    @Override
    public Subscriber save(Subscriber subscriber) {
        return this.subscriberRepository.save(subscriber);
    }

    @Override
    public void deleteById(Long id) {
        this.subscriberRepository.deleteById(id);
    }

    @Override
    public List<Subscriber> findSubscribersByUser_Id(Long userId) {
        return this.subscriberRepository.findSubscribersByUser_Id(userId);
    }

    @Override
    public Optional<Subscriber> findSubscribersByUser_IdAndMember_Id(Long userId, Long memberId) {
        return this.subscriberRepository.findSubscribersByUser_IdAndMember_Id(userId, memberId);
    }

    @Override
    public List<Subscriber> findSubscribersByMember_Id(Long memberId) {
        return this.subscriberRepository.findSubscribersByMember_Id(memberId);
    }
}

package com.thang.youtube.service.dislikeReply;

import com.thang.youtube.model.entity.DislikeReply;
import com.thang.youtube.repository.IDislikeReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DislikeReplyService implements IDislikeReplyService{
    @Autowired
    private IDislikeReplyRepository dislikeReplyRepository;
    @Override
    public Iterable<DislikeReply> getAll() {
        return this.dislikeReplyRepository.findAll();
    }

    @Override
    public Optional<DislikeReply> getById(Long id) {
        return this.dislikeReplyRepository.findById(id);
    }

    @Override
    public DislikeReply save(DislikeReply dislikeReply) {
        return this.dislikeReplyRepository.save(dislikeReply);
    }

    @Override
    public void deleteById(Long id) {
        this.dislikeReplyRepository.deleteById(id);
    }

    @Override
    public Optional<DislikeReply> findDislikeReplyByReplyComment_IdAndUser_Id(Long replyId, Long userId) {
        return this.dislikeReplyRepository.findDislikeReplyByReplyComment_IdAndUser_Id(replyId, userId);
    }
}

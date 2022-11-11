package com.thang.youtube.service.likeReply;

import com.thang.youtube.model.entity.LikeReply;
import com.thang.youtube.repository.ILikeReplyRepository;
import com.thang.youtube.service.reply.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeReplyService implements ILikeReplyService {
    @Autowired
    private ILikeReplyRepository likeReplyRepository;

    @Override
    public Iterable<LikeReply> getAll() {
        return this.likeReplyRepository.findAll();
    }

    @Override
    public Optional<LikeReply> getById(Long id) {
        return this.likeReplyRepository.findById(id);
    }

    @Override
    public LikeReply save(LikeReply likeReply) {
        return this.likeReplyRepository.save(likeReply);
    }

    @Override
    public void deleteById(Long id) {
        this.likeReplyRepository.deleteById(id);
    }

    @Override
    public Optional<LikeReply> findLikeReplyByReplyComment_IdAndUser_Id(Long replyId, Long userId) {
        return this.likeReplyRepository.findLikeReplyByReplyComment_IdAndUser_Id(replyId, userId);
    }

    @Override
    public List<LikeReply> findLikeReplyByReplyComment_Id(Long replyId) {
        return this.likeReplyRepository.findLikeReplyByReplyComment_Id(replyId);
    }
}

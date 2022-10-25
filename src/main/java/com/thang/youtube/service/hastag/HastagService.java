package com.thang.youtube.service.hastag;

import com.thang.youtube.model.entity.Hastag;
import com.thang.youtube.repository.IHastagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HastagService implements IHastagService{
    @Autowired
    private IHastagRepository hastagRepository;
    @Override
    public Iterable<Hastag> getAll() {
        return this.hastagRepository.findAll();
    }

    @Override
    public Optional<Hastag> getById(Long id) {
        return this.hastagRepository.findById(id);
    }

    @Override
    public Hastag save(Hastag hastag) {
        return this.hastagRepository.save(hastag);
    }

    @Override
    public void deleteById(Long id) {
        this.hastagRepository.deleteById(id);
    }
}

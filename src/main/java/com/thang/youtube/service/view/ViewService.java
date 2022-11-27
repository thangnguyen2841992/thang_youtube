package com.thang.youtube.service.view;

import com.thang.youtube.model.entity.View;
import com.thang.youtube.repository.IViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ViewService implements IViewService{
    @Autowired
    private IViewRepository viewRepository;
    @Override
    public Iterable<View> getAll() {
        return this.viewRepository.findAll();
    }

    @Override
    public Optional<View> getById(Long id) {
        return this.viewRepository.findById(id);
    }

    @Override
    public View save(View view) {
        return this.viewRepository.save(view);
    }

    @Override
    public void deleteById(Long id) {
        this.viewRepository.deleteById(id);
    }
}

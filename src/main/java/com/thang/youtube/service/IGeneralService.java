package com.thang.youtube.service;

import java.util.Optional;

public interface IGeneralService<T>{
    Iterable<T> getAll();

    Optional<T> getById(Long id);

    T save(T t);

    void deleteById(Long id);
}

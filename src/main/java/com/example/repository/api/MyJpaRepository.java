package com.example.repository.api;

import com.example.model.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface MyJpaRepository <T extends AbstractEntity, U> {

    T save(T entity);

    Optional<T> findById(U id);

    List<T> findAll();
}

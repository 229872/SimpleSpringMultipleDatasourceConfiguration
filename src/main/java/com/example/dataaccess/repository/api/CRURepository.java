package com.example.dataaccess.repository.api;

import com.example.dataaccess.model.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface CRURepository<T extends AbstractEntity, U> {

    List<T> findAll();

    Optional<T> findById(U id);

    T save(T entity);
}

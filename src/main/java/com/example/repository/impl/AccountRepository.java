package com.example.repository.impl;

import com.example.model.Account;
import com.example.repository.api.MyJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional("transactionManager1")
public class AccountRepository implements MyJpaRepository<Account, Long> {


    @PersistenceContext(unitName = "postgres")
    private EntityManager entityManager;


    @Override
    public Account save(Account entity) {
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }

        return entity;
    }

    @Override
    public Optional<Account> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Account.class, id));
    }

    @Override
    public List<Account> findAll() {
        String tableName = Account.class.getName();
        return entityManager.createQuery("FROM %s".formatted(tableName), Account.class)
            .getResultList();
    }
}

package com.example;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;


@SpringBootApplication
public class SpringJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaApplication.class, args);
    }

    @Bean(name = "postgres")
    public EntityManagerFactory getEntityManagerFactory() {
         return Persistence.createEntityManagerFactory("postgres");
    }

    @Bean(name = "postgres2")
    public EntityManagerFactory getEntityManagerFactory2() {
        return Persistence.createEntityManagerFactory("postgres2");

    }


    @Bean(name = "transactionManager1")
    public PlatformTransactionManager transactionManager1(
        @Qualifier("postgres") EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean(name = "transactionManager2")
    public PlatformTransactionManager transactionManager2(
        @Qualifier("postgres2") EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }


}

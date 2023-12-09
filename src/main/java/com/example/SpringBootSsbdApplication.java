package com.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class,
    XADataSourceAutoConfiguration.class
})
@RequiredArgsConstructor
@Slf4j
public class SpringBootSsbdApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSsbdApplication.class, args);
    }

    private final ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        var txManagers = applicationContext.getBeansOfType(PlatformTransactionManager.class).keySet();
        log.info("TX managers count: {}, TX managers: {}", txManagers.size(), txManagers);
    }
}

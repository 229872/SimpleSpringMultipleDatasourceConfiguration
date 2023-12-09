package com.example.config.bean;

import com.example.config.defaults.JpaConfigurable;
import com.example.config.property.DataSourceProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
    entityManagerFactoryRef = "initEmFactory",
    transactionManagerRef = "initTxManager",
    basePackages = {"com.example.dataaccess.repository.impl.init"}
)
public class InitConnectionConfig implements JpaConfigurable {

    public static final String PROPERTIES_ROOT = "db.datasource.init";

    @Override
    @ConfigurationProperties(PROPERTIES_ROOT)
    @Bean("initDataSourceProperties")
    public DataSourceProperties dataSourcePropertiesBean() {
        return JpaConfigurable.super.dataSourcePropertiesBean();
    }

    @Override
    @Bean("initDataSource")
    public DataSource dataSourceBean(@Qualifier("initDataSourceProperties") DataSourceProperties properties) {
        return JpaConfigurable.super.dataSourceBean(properties);
    }

    @Override
    @Bean("initDataSourceInitializer")
    public DataSourceInitializer dataSourceInitializerBean(@Qualifier("initDataSource") DataSource dataSource,
                                                           @Qualifier("initDataSourceProperties") DataSourceProperties properties) {
        return JpaConfigurable.super.dataSourceInitializerBean(dataSource, properties);
    }

    @Override
    @Bean("initEmFactory")
    public LocalContainerEntityManagerFactoryBean emFactoryBean(@Qualifier("initDataSource") DataSource dataSource,
                                                                @Qualifier("initDataSourceProperties") DataSourceProperties properties) {
        return JpaConfigurable.super.emFactoryBean(dataSource, properties);
    }

    @Override
    @Bean("initTxManager")
    public JpaTransactionManager txManagerBean(@Qualifier("initEmFactory") LocalContainerEntityManagerFactoryBean emFactory) {
        return JpaConfigurable.super.txManagerBean(emFactory);
    }
}

package com.example.config.bean;

import com.example.config.defaults.JpaConfigurable;
import com.example.config.listener.LoggingMokTransactionListener;
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
    entityManagerFactoryRef = "mokEmFactory",
    transactionManagerRef = "mokTxManager",
    basePackages = "com.example.dataaccess.repository.impl.mok"
)
public class MokConnectionConfig implements JpaConfigurable {

    public static final String PROPERTIES_ROOT = "db.datasource.mok";

    @Override
    @ConfigurationProperties(PROPERTIES_ROOT)
    @Bean("mokDataSourceProperties")
    public DataSourceProperties dataSourcePropertiesBean() {
        return JpaConfigurable.super.dataSourcePropertiesBean();
    }

    @Override
    @Bean("mokDataSource")
    public DataSource dataSourceBean(@Qualifier("mokDataSourceProperties") DataSourceProperties properties) {
        return JpaConfigurable.super.dataSourceBean(properties);
    }

    @Override
    @Bean("mokDataSourceInitializer")
    public DataSourceInitializer dataSourceInitializerBean(@Qualifier("mokDataSource") DataSource dataSource,
                                                           @Qualifier("mokDataSourceProperties") DataSourceProperties properties) {
        return JpaConfigurable.super.dataSourceInitializerBean(dataSource, properties);
    }

    @Override
    @Bean("mokEmFactory")
    public LocalContainerEntityManagerFactoryBean emFactoryBean(@Qualifier("mokDataSource") DataSource dataSource,
                                                                @Qualifier("mokDataSourceProperties") DataSourceProperties properties) {
        return JpaConfigurable.super.emFactoryBean(dataSource, properties);
    }

    @Override
    @Bean("mokTxManager")
    public JpaTransactionManager txManagerBean(@Qualifier("mokEmFactory") LocalContainerEntityManagerFactoryBean emFactory) {
        var jpaTxManager = JpaConfigurable.super.txManagerBean(emFactory);
        jpaTxManager.addListener(new LoggingMokTransactionListener());
        return jpaTxManager;
    }
}

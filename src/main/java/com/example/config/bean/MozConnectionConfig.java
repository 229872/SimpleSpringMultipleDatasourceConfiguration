package com.example.config.bean;

import com.example.config.defaults.JpaConfigurable;
import com.example.config.listener.LoggingMozTransactionListener;
import com.example.config.property.DataSourceProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
    entityManagerFactoryRef = "mozEmFactory",
    transactionManagerRef = "mozTxManager",
    basePackages = "com.example.dataaccess.repository.impl.moz"
)
public class MozConnectionConfig implements JpaConfigurable {

    public static final String PROPERTIES_ROOT = "db.datasource.moz";

    @Override
    @ConfigurationProperties(PROPERTIES_ROOT)
    @Bean("mozDataSourceProperties")
    public DataSourceProperties dataSourcePropertiesBean() {
        return JpaConfigurable.super.dataSourcePropertiesBean();
    }

    @Override
    @Bean("mozDataSource")
    public DataSource dataSourceBean(@Qualifier("mozDataSourceProperties") DataSourceProperties properties) {
        return JpaConfigurable.super.dataSourceBean(properties);
    }

    @Override
    @Bean("mozDataSourceInitializer")
    public DataSourceInitializer dataSourceInitializerBean(@Qualifier("mozDataSource") DataSource dataSource,
                                                           @Qualifier("mozDataSourceProperties") DataSourceProperties properties) {
        return JpaConfigurable.super.dataSourceInitializerBean(dataSource, properties);
    }

    @Override
    @Bean("mozEmFactory")
    public LocalContainerEntityManagerFactoryBean emFactoryBean(@Qualifier("mozDataSource") DataSource dataSource,
                                                                @Qualifier("mozDataSourceProperties") DataSourceProperties properties) {
        return JpaConfigurable.super.emFactoryBean(dataSource, properties);
    }

    @Override
    @Bean("mozTxManager")
    public JpaTransactionManager txManagerBean(@Qualifier("mozEmFactory") LocalContainerEntityManagerFactoryBean emFactory) {
        var jpaTxManager = JpaConfigurable.super.txManagerBean(emFactory);
        jpaTxManager.addListener(new LoggingMozTransactionListener());
        return jpaTxManager;
    }
}

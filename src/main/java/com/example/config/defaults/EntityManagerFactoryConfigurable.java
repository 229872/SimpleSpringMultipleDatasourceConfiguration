package com.example.config.defaults;

import com.example.config.property.DataSourceProperties;
import com.example.dataaccess.model.AbstractEntity;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

public interface EntityManagerFactoryConfigurable {

    /**
     * @apiNote Override this method and annotate with @Bean with unique name and use @Qualifier for parameters
     * @apiNote You must set persistence unit name
     */
    default LocalContainerEntityManagerFactoryBean emFactoryBean(DataSource dataSource, DataSourceProperties properties) {
        var emFactory = new LocalContainerEntityManagerFactoryBean();

        emFactory.setPersistenceUnitName(properties.getPersistenceUnitName());
        emFactory.setDataSource(dataSource);
        emFactory.setPersistenceProvider(new HibernatePersistenceProvider());
        emFactory.setJpaProperties(getJpaProperties(properties.getJpa()));
        emFactory.setPackagesToScan(AbstractEntity.class.getPackageName());

        return emFactory;
    }

    private Properties getJpaProperties(DataSourceProperties.JpaProperties properties) {
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("jakarta.persistence.schema-generation.database.action", properties.getDatabaseAction().getActionName());
        return jpaProperties;
    }
}

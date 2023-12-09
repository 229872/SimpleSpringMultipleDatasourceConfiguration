package com.example.config.defaults;

public interface JpaConfigurable extends DataSourceConfigurable, EntityManagerFactoryConfigurable, TransactionManagerConfigurable {

    /**
     * Override all methods and make all requirements for extended apis.
     */
}

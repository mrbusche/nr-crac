package com.crac;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "oneEntityManagerFactory",
        transactionManagerRef = "oneTransactionManager",
        basePackages = {"com.crac.one"})
@EnableTransactionManagement
public class OneDataSourceConfig {

    @Bean
    public DataSource oneDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:db-1")
                .build();
    }

    @Bean
    public EntityManagerFactory oneEntityManagerFactory(DataSource oneDataSource) {
        var jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        var factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(jpaVendorAdapter);
        factory.setDataSource(oneDataSource);
        factory.setPackagesToScan("com.crac.one");
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager oneTransactionManager(EntityManagerFactory oneEntityManagerFactory) {
        var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(oneEntityManagerFactory);
        return transactionManager;
    }
}

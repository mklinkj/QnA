package org.mklinkj.qna.spring_data_jpa_pageable.config;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.Properties;
import java.util.stream.IntStream;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.qna.spring_data_jpa_pageable.domain.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Slf4j
@RequiredArgsConstructor
@ComponentScan(basePackages = "org.mklinkj.qna")
@EnableJpaRepositories(basePackages = "org.mklinkj.qna.spring_data_jpa_pageable.repository")
@PropertySource({"classpath:database.properties"})
@Configuration
public class RootConfig {

  private final Environment env;

  @Bean
  DataSource dataSource() {
    SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
    dataSource.setDriverClass(org.hsqldb.jdbc.JDBCDriver.class);
    dataSource.setUrl(env.getProperty("jdbc.url"));
    dataSource.setUsername(env.getProperty("jdbc.username"));
    dataSource.setPassword(env.getProperty("jdbc.password"));
    return dataSource;
  }

  @Bean
  LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();

    emfBean.setDataSource(dataSource);
    emfBean.setPersistenceUnitName("PAGEABLE_TEST");
    emfBean.setPackagesToScan("org.mklinkj.qna.spring_data_jpa_pageable.domain");

    HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
    hibernateJpaVendorAdapter.setDatabase(Database.HSQL);
    hibernateJpaVendorAdapter.setShowSql(true);
    emfBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);

    Properties jpaProp = new Properties();
    jpaProp.put("hibernate.format_sql", true);
    jpaProp.put("hibernate.id.new_generator_mappings", true);
    jpaProp.put("hibernate.hbm2ddl.auto", "none");
    jpaProp.put("hibernate.hbm2ddl.charset_name", "UTF-8");

    emfBean.setJpaProperties(jpaProp);

    return emfBean;
  }

  @Bean
  JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}

package org.mklinkj.qna.spring_data_jpa_pageable.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import java.util.Properties;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@RequiredArgsConstructor
@ComponentScan(basePackages = "org.mklinkj.qna")
@MapperScan(basePackages = {"org.mklinkj.qna.spring_data_jpa_pageable.mapper"})
@EnableJpaRepositories(basePackages = "org.mklinkj.qna.spring_data_jpa_pageable.repository")
@PropertySource({"classpath:database.properties"})
@Configuration
public class RootConfig {

  private final Environment env;

  // https://github.com/brettwooldridge/HikariCP#rocket-initialization
  @Bean
  DataSource dataSource() {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setDriverClassName(env.getProperty("jdbc.driver"));
    hikariConfig.setJdbcUrl(env.getProperty("jdbc.url"));
    hikariConfig.setUsername(env.getProperty("jdbc.username"));
    hikariConfig.setPassword(env.getProperty("jdbc.password"));
    return new HikariDataSource(hikariConfig);
  }

  @Bean
  LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();

    emfBean.setDataSource(dataSource);
    emfBean.setPersistenceUnitName("PAGEABLE_TEST");
    emfBean.setPackagesToScan("org.mklinkj.qna.spring_data_jpa_pageable.entity");

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
  SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);

    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
    sqlSessionFactoryBean.setTypeAliasesPackage("org.mklinkj.qna.spring_data_jpa_pageable.vo");
    return sqlSessionFactoryBean.getObject();
  }

  @Bean
  JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}

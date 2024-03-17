package com.mateoarias.studentsrestapi.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfiguration {

  @Value("${spring.datasource.url}")
  String dataSourceUrl;

  @Value("${spring.datasource.password}")
  String dataSourcePassword;

  @Value("${spring.datasource.username}")
  String dataSourceUsername;

  @Bean
  DataSource dataSource() {
    var configuration = new HikariConfig();

    configuration.setJdbcUrl(dataSourceUrl);
    configuration.setUsername(dataSourceUsername);
    configuration.setPassword(dataSourcePassword);

    return new HikariDataSource(configuration);
  }
}

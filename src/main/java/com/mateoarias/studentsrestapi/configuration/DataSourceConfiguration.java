package com.mateoarias.studentsrestapi.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Configuration class for the DataSource of the application. */
@Configuration
class DataSourceConfiguration {

  /** The DataSource URL taken from the application.properties file. */
  @Value("${spring.datasource.url}")
  private String dataSourceUrl;

  /** The DataSource password taken from the application.properties file. */
  @Value("${spring.datasource.password}")
  private String dataSourcePassword;

  /** The DataSource username taken from the application.properties file. */
  @Value("${spring.datasource.username}")
  private String dataSourceUsername;

  /**
   * The DataSource for the application built from the specified properties in the
   * application.properties file. This specific DataSource is a connection pool one.
   *
   * @return The DataSource for the application built from the specified properties. Note that this
   *     DataSource is a connection pool.
   */
  @Bean
  DataSource connectionPoolDataSource() {
    var hikariConfiguration = new HikariConfig();

    hikariConfiguration.setJdbcUrl(dataSourceUrl);
    hikariConfiguration.setUsername(dataSourceUsername);
    hikariConfiguration.setPassword(dataSourcePassword);

    return new HikariDataSource(hikariConfiguration);
  }
}

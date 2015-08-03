package com.ricston.encryptedpwd;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class App extends SpringBootServletInitializer {

	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	@Value("${db.driverclassname}")
	private String dbDriverClassName;

	@Value("${db.url}")
	private String dbUrl;

	@Value("${db.username}")
	private String dbUsername;

	@Value("${db.password}")
	private String dbPassword;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dbDriverClassName);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);

		LOG.info("dataSource url: " + dataSource.getUrl());
		return dataSource;
	}

	@Bean
	public ContactRepository msgRepo() {
		return new MySQLContactRepository();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}

}

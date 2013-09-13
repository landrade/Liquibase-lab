package br.com.landrade.configurations;

import javax.inject.Inject;
import javax.sql.DataSource;

import liquibase.integration.spring.SpringLiquibase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource(value = { "classpath:database.properties" })
public class PersistenceConfig {

	@Inject
	Environment env;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("database.driver"));
		dataSource.setUrl(env.getProperty("database.url"));
		dataSource.setUsername(env.getProperty("database.username"));
		dataSource.setPassword(env.getProperty("database.password"));
		return dataSource;
	}
	
	@Bean
	public SpringLiquibase springLiquibase() {
		SpringLiquibase springLiquibase = new SpringLiquibase();
		springLiquibase.setDataSource(dataSource());
		springLiquibase.setChangeLog("classpath:db-migrations/Main.xml");
		return springLiquibase;
	}

}

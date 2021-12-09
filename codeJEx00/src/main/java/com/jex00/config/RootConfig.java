package com.jex00.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = {"com.jex00.sample"})
@MapperScan(basePackages = {"com.jex00.mapper"})
public class RootConfig {

	@Bean
	public DataSource dataSouce() {
		HikariConfig hikariConfig = new HikariConfig();
	    //hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	    //hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");
		
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@localhost:1521:XE");
		
	    hikariConfig.setUsername("scott");
	    hikariConfig.setPassword("tiger");

	    HikariDataSource dataSource = new HikariDataSource(hikariConfig);

	    return dataSource; 
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSouce());
		
		return sqlSessionFactory.getObject();
	}
}

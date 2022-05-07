package com.jindo.core.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.jindo.core.util.AES;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Hikari là một datasource được tích hợp sẵn trong SpringBoot rồi, nên không cần phải import thêm lib
 * Hướng dẫn là sử dụng Hikari DataSource
 * Vì: config pool size, sessionTimeOut.
 *
 */
@Configuration
public class DatabaseConfig {

	@Value("${db.datasource.url}")
	private String urlDB;
	
	@Value("${db.datasource.username}")
	private String userName;
	
	@Value("${db.datasource.password}")
	private String passWord;
	
	@Value("${db.datasource.driverClassName}")
	private String driverClassName;
	
	private String secretKey = "123456aA@_qwert";
	
	/**
	 * BeanName mặc định là DataSource
	 * Thay vì sử dụng các DataSource khác -> mình sẽ sử dụng HikariConfig
	 * Chúng có một số ưu điểm mạnh mẽ mang lại.
	 * 
	 */
	@Bean(name = "dataSource")
    public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(AES.decrypt(urlDB, secretKey));
		hikariConfig.setUsername(AES.decrypt(userName, secretKey));
		hikariConfig.setPassword(AES.decrypt(passWord, secretKey));
		hikariConfig.setDriverClassName(driverClassName);
		
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		return hikariDataSource;
	}
	
	/**
	 * Cấu hình làm sao cho trả về một DataSourceTransactionManager
	 * 
	 * 
	 */
	@Bean(name = "transactionManager")
	public DataSourceTransactionManager dataSourceTransactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	/**
	 * Cấu hình lên một Factory quản lý các Session làm việc với sql:
	 * Annotation Qualifier là gì?
	 * 
	 */
	@Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
 
        /**
         * Cấu hình đến file Mapper location
         */
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:com/jindo/core/mapper/sql/*.xml"));
 
        
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sessionFactory.setConfiguration(configuration);
        return sessionFactory.getObject();
    }
	

}

package com.quiz.license.common.config.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    @Qualifier("bankHikariConfig")
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariConfig bankHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    @Qualifier("bankDataSource")
    public DataSource bankDataSource(){
        return new HikariDataSource(bankHikariConfig());
    }

    @Bean(name = "bankSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("bankDataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "bankSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("bankSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

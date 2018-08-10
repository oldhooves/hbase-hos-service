package com.sunda.bigdata.hos.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;


/**
 * Created by 老蹄子 on 2018/8/10 下午8:38
 */
@Configuration
@MapperScan(basePackages = HosDataSourceConfig.PACKAGE,
        sqlSessionFactoryRef = "HosSqlSessionFactory")
public class HosDataSourceConfig {


    static final String PACKAGE = "com.sunda.bigdata.hos.**";

    /**
     * hosDataSource.
     *
     * @return DataSource DataSource
     * @throws IOException IOException
     */
    @Bean(name = "HosDataSource")
    @Primary
    public DataSource hosDataSource() throws IOException {
        ResourceLoader loader = new DefaultResourceLoader();
        InputStream inputStream = loader.getResource("classpath:application.properties")
                .getInputStream();
        Properties properties = new Properties();
        properties.load(inputStream);
        Set<Object> keys = properties.keySet();
        Properties dsproperties = new Properties();
        for (Object key : keys) {
            if (key.toString().startsWith("datasource")) {
                dsproperties.put(key.toString().replace("datasource.", ""), properties.get(key));
            }
        }
        HikariDataSourceFactory factory = new HikariDataSourceFactory();
        factory.setProperties(dsproperties);
        inputStream.close();
        return factory.getDataSource();
    }

    /**
     * hosSqlSessionFactory.
     */
    @Bean(name = "HosSqlSessionFactory")
    @Primary
    public SqlSessionFactory hosSqlSessionFactory(
            @Qualifier("HosDataSource") DataSource phoenixDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(phoenixDataSource);
        ResourceLoader loader = new DefaultResourceLoader();
        String resource = "classpath:mybatis-config.xml";
        factoryBean.setConfigLocation(loader.getResource(resource));
        factoryBean.setSqlSessionFactoryBuilder(new SqlSessionFactoryBuilder());
        return factoryBean.getObject();
    }
}

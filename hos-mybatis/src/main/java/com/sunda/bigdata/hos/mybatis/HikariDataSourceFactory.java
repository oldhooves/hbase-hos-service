package com.sunda.bigdata.hos.mybatis;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * Created by 老蹄子 on 2018/8/10 下午8:29
 */
public class HikariDataSourceFactory extends UnpooledDataSourceFactory {


    public HikariDataSourceFactory() {
        this.dataSource = new HikariDataSource();
    }
}

package com.example.djran.switches.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * 自定义数据源配置
 */
@Configuration
public class JdbcConfig {

    /**
     *  数据源1
     * @return
     */
    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource")
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     *  数据源2
     * @return
     */
    @Bean(name = "oracleDataSource")
    @ConfigurationProperties(prefix = "oracle.datasource")
    public DataSource clusterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 数据源3
     * @return
     */
    @Bean("djranDataSource")
    @ConfigurationProperties(prefix = "djran.datasource")
    public DataSource djranDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 用于动态切换数据源
     * @param mysqlDataSource
     * @param oracleDataSource
     * @param djranDataSource
     * @return
     */
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource(@Qualifier("mysqlDataSource") DataSource mysqlDataSource,
                                        @Qualifier("oracleDataSource") DataSource oracleDataSource,
                                        @Qualifier("djranDataSource") DataSource djranDataSource) {
        DynamicDataSourceResolver resolver = new DynamicDataSourceResolver();
        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put("MYSQL".toString(),mysqlDataSource);
        dataSources.put("ORACLE",oracleDataSource);
        dataSources.put("DJRAN",djranDataSource);
        resolver.setTargetDataSources(dataSources);
        //设置默认数据源，当无法映射到数据源时会使用默认数据源
        resolver.setDefaultTargetDataSource(oracleDataSource);
        resolver.afterPropertiesSet();
        return resolver;
    }

    /**
     * 设置SqlSessionFactory ，使用动态切换数据源
     * @param mysqlDataSource
     * @param oracleDataSource
     * @param djranDataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "dynamicSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mysqlDataSource") DataSource mysqlDataSource,
                                               @Qualifier("oracleDataSource") DataSource oracleDataSource,
                                               @Qualifier("djranDataSource") DataSource djranDataSource)
            throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(this.dynamicDataSource(mysqlDataSource, oracleDataSource,djranDataSource));
        return fb.getObject();
    }

    /**
     * 设置MyBatis包扫描路径 ，使用上面创建的设置SqlSessionFactory
     * @return
     */
    @Bean
    public MapperScannerConfigurer mappperConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("com.example.djran.switches.mapper");
        msc.setSqlSessionFactoryBeanName("dynamicSqlSessionFactory");
        return msc;
    }

}

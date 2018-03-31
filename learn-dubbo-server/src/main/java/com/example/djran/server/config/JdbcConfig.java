package com.example.djran.server.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.example.djran.api.model.Posts;
import com.example.djran.server.dao.PostsDao;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created on 2018/3/29
 *
 * @author d.djran@gmail.com
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = PostsDao.class)
public class JdbcConfig {
    @Bean("dataSource")
    @ConfigurationProperties(prefix = "djran.datasource")
    public DataSource dataSource(){
        return DruidDataSourceBuilder.create().build();
    }

//    @Value("${djran.datasource.url}")
//    private String url;
//    @Value("${djran.datasource.username}")
//    private String username;
//    @Value("${djran.datasource.password}")
//    private String password;
//
//
//    @Bean("dataSource")
//    @Primary
//    public DataSource dataSource()  {
//        DruidDataSource basicDataSource = new DruidDataSource();
//        basicDataSource.setUrl(url);
//        basicDataSource.setUsername(username);
//        basicDataSource.setPassword(password);
//        basicDataSource.setInitialSize(10);
//        basicDataSource.setMaxActive(50);
////        basicDataSource.setFilters("stat,wall");
//        log.info("使用本地数据源：" + basicDataSource.getUrl());
//        return basicDataSource;
//    }
    @Bean(name = "entityManager")
    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) throws SQLException {
        return entityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactory" )
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) throws SQLException {
        return builder
                .dataSource(this.dataSource())
                //设置实体类所在位置
                .packages(Posts.class)
                .persistenceUnit("pubPersistenceUnit")
                .build();
    }

    @Bean(name = "transactionManager")
    @Primary
    PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) throws SQLException {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }

}

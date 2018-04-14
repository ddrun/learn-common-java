package com.example.djran.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Created on 2018/4/12
 *
 * @EnableWebSecurity 启用Security
 * @EnableGlobalMethodSecurity 基于方法的权限控制
 *
 * @author d.djran@gmail.com
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //如下请求路径下皆可访问
                .antMatchers("/css/**","/js/**","/fonts/**","/index").permitAll()
                //需要user角色才能访问
                .antMatchers("/user/**").hasRole("USER")
                //需要admin角色才能访问
                .antMatchers("/admins/**").hasRole("ADMIN")
                .and()
                //表单登陆
                .formLogin().loginPage("/login").failureUrl("/login-error")
                .and()
                //登出处理
                .logout().logoutUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
        ;
    }

    /**
     * 用户信息
     * @return
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager=new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password("222222").roles("USER").build());
        manager.createUser(User.withUsername("admin").password("123456").roles("USER","ADMIN").build());
        return manager;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("USER");
        auth.userDetailsService(userDetailsService());
    }
}

package com.example.djran.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * Created on 2018/4/3
 *
 * @author dengjr
 */
@Configuration
@ImportResource({"dubbo/*.xml"})
@PropertySource("classpath:dubbo/dubbo-client.properties")
public class DubboConfig {
}

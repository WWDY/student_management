package com.daiju.mp.config;

import com.daiju.mp.Mysqlinjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author WDY
 * @Date 2020-11-14 15:13
 * @Description TODO
 */
@Configuration
public class Config {
    @Bean
    public Mysqlinjector mysqlinjector(){
        return new Mysqlinjector();
    }
}

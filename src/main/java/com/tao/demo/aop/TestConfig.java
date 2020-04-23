package com.tao.demo.aop;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class TestConfig {

    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }
    
}

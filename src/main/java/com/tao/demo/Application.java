package com.tao.demo;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.tao.frameworks.base.spring.RequestMappingHandlerAdapterModify;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.tao.demo.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        return page;
    }

    @Bean
    public RequestMappingHandlerAdapterModify requestMappingHandlerAdapterModify() {
        return new RequestMappingHandlerAdapterModify();
    }

//    @Bean
//    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
//        return factory -> factory.setContextPath("/keeper");
//    }

}

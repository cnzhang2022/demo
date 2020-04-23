package com.tao.demo.config;

import com.tao.demo.aop.CrossAccessFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    /**
     * 设置允许可以跨域访问
     *
     * @return CrossAccessFilter
     */
    @Bean
    public CrossAccessFilter crossAccessFilter() {
        return new CrossAccessFilter();
    }

}

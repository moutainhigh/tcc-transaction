package com.github.qichengjian.tcc.springcloud.configuration;

import com.github.qichengjian.tcc.springcloud.feign.TccFeignInterceptor;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TccFeignConfiguration {

    @Bean
    @Qualifier("")
    public RequestInterceptor tccFeignInterceptor() {
        return new TccFeignInterceptor();
    }
}

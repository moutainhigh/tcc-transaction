package com.github.qichengjian.tcc.spring.boot.starter.configuration;

import com.github.qichengjian.tcc.core.bootstrap.TccTransactionBootstrap;
import com.github.qichengjian.tcc.core.service.TccInitService;
import com.github.qichengjian.tcc.spring.boot.starter.config.TccConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableConfigurationProperties(TccConfigProperties.class)
@ComponentScan(basePackages = "com.github.qichengjian")
public class TccAutoConfiguration {

    private TccConfigProperties tccConfigProperties;

    @Autowired(required = false)
    public TccAutoConfiguration(TccConfigProperties tccConfigProperties) {
        this.tccConfigProperties = tccConfigProperties;
    }

    @Bean
    public TccTransactionBootstrap tccTransactionBootstrap(TccInitService tccInitService) {
        TccTransactionBootstrap bootstrap = new TccTransactionBootstrap(tccInitService);
        bootstrap.setRetryMax(tccConfigProperties.getRetryMax());
        bootstrap.setSerializer(tccConfigProperties.getSerializer());
        bootstrap.setTccDbConfig(tccConfigProperties.getTccDbConfig());
        return bootstrap;
    }


}

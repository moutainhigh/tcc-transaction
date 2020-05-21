package com.github.qichengjian.tcc.spring.boot.starter.config;

import com.github.qichengjian.tcc.common.config.TccConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tcc", ignoreInvalidFields = true)
public class TccConfigProperties extends TccConfig {
}

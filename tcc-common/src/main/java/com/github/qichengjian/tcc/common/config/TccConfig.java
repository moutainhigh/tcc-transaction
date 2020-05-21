package com.github.qichengjian.tcc.common.config;

import lombok.Data;

@Data
public class TccConfig {

    /**
     * 序列化方式
     */
    private String serializer = "kryo";
    /**
     * 最大重试次数
     */
    private int retryMax = 3;

    /**
     * 支持的持久化方式
     */
    private String repositorySupport = "db";

    /**
     * 配置数据库来纪录重试操作
     */
    private TccDbConfig tccDbConfig;

}

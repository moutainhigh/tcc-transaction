package com.github.qichengjian.tcc.common.config;

import lombok.Data;

@Data
public class TccDbConfig {

    /**
     * driverClassName
     */
    private String driverClassName = "com.mysql.jdbc.Driver";

    /**
     * url
     */
    private String url;

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;
}

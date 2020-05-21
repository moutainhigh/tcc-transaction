package com.github.qichengjian.tcc.core.helper;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * 单例模式的SpringBean工具类
 */
public class SpringBeanUtils {

    private static final SpringBeanUtils INSTANCE = new SpringBeanUtils();

    private ConfigurableApplicationContext cfgContext;

    private SpringBeanUtils() {
        if (INSTANCE != null) {
            throw new Error("error");
        }
    }

    public static SpringBeanUtils getInstance() {
        return INSTANCE;
    }

    public <T> T getBean(Class<T> clazz) {
        T bean;
        try {
            bean = cfgContext.getBean(clazz);
        } catch (Exception e) {
            bean = null;
        }
        return bean;
    }
}

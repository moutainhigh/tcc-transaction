package com.github.qichengjian.tcc.common.utils.extension;

import java.util.ServiceLoader;

public class ServiceBootstrap {

    /**
     * 加载所有的spi扩展
     * @param clazz
     * @param <S>
     * @return
     */
    public static <S>ServiceLoader<S> loadAll(final Class<S> clazz) {
        return ServiceLoader.load(clazz);
    }
}

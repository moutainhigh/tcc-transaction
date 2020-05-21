package com.github.qichengjian.tcc.common.utils.extension;

import com.github.qichengjian.tcc.common.annotation.TccSPI;
import com.github.qichengjian.tcc.common.exception.TccException;

import java.util.Objects;
import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

/**
 * 扩展类型加载器
 */
public class ExtensionLoader<T> {

    private Class<T> type;

    private ExtensionLoader(Class<T> type) {
        this.type = type;
    }

    public static <T> ExtensionLoader<T> getExtensionLoader(final Class<T> clazz) {
        return new ExtensionLoader<>(clazz);
    }
    /**
     * 返回配置的spi实现类，否则抛出异常
     * @param value
     * @return
     */
    public T getActivateExtension(final String value) {
        ServiceLoader<T> loader = ServiceBootstrap.loadAll(type);
        return StreamSupport.stream(loader.spliterator(), false)
                .filter(e-> Objects.equals(e.getClass().getAnnotation(TccSPI.class).value(), value))
                .findFirst().orElseThrow(() -> new TccException("请检查你的配置"));
    }
}

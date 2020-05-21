package com.github.qichengjian.tcc.common.serializer;

import com.github.qichengjian.tcc.common.annotation.TccSPI;
import com.github.qichengjian.tcc.common.exception.TccException;

@TccSPI
public interface ObjectSerializer {

    /**
     * 将对象序列化为字节数组
     * @param obj
     * @return
     * @throws TccException
     */
    byte[] serialize(Object obj) throws TccException;

    /**
     * 将字节数组反序列化成对象
     * @param param
     * @param clazz
     * @param <T>
     * @throws TccException
     */
    <T> T deSerialize(byte[] param, Class<T> clazz) throws TccException;
}

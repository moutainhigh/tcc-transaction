package com.github.qichengjian.tcc.common.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.qichengjian.tcc.common.annotation.TccSPI;
import com.github.qichengjian.tcc.common.exception.TccException;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@TccSPI(value = "kryo")
public class KryoSerializer implements ObjectSerializer{
    @Override
    public byte[] serialize(Object obj) throws TccException {
        byte[] bytes;
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); Output output = new Output(outputStream)) {
            //获取kryo对象
            Kryo kryo = new Kryo();
            kryo.writeObject(output, obj);
            bytes = output.toBytes();
            output.flush();
        } catch (IOException e) {
            throw new TccException("kryo serialize error: " + e.getMessage());
        }
        return bytes;
    }

    @Override
    public <T> T deSerialize(byte[] param, Class<T> clazz) throws TccException {
        T obj;
        try(ByteInputStream inputStream = new ByteInputStream(); Input input = new Input(inputStream)) {
            //获取kryo对象
            Kryo kryo = new Kryo();
            obj = kryo.readObject(input, clazz);
        } catch (IOException e) {
            throw new TccException("kryo deSerialize error: " + e.getMessage());
        }
        return obj;
    }
}

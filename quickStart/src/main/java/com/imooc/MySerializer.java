package com.imooc;

import org.apache.kafka.common.serialization.Serializer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class MySerializer implements Serializer {
    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Object o) {
        if(o != null){
            User user = (User) o;
            byte[] idBytes = (user.getId()+"").getBytes(StandardCharsets.UTF_8);
            byte[] nameBytes = user.getName().getBytes(StandardCharsets.UTF_8);

            ByteBuffer buffer = ByteBuffer.allocate(4+idBytes.length+4+nameBytes.length);
            buffer.putInt(idBytes.length);
            buffer.put(idBytes);
            buffer.putInt(nameBytes.length);
            buffer.put(nameBytes);

            return buffer.array();
        }

        return new byte[0];
    }

    @Override
    public void close() {

    }
}

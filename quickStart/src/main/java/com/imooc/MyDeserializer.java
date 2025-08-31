package com.imooc;

import org.apache.kafka.common.serialization.Deserializer;
import scala.util.control.Exception;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class MyDeserializer implements Deserializer {
    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        if(bytes.length != 0){
            ByteBuffer buffer = ByteBuffer.wrap(bytes);
            int idLength = buffer.getInt();
            byte[] idBytes = new byte[idLength];
            buffer.get(idBytes);

            int nameLength = buffer.getInt();
            byte[] nameBytes = new byte[nameLength];
            buffer.get(nameBytes);

            int id = new BigInteger(idBytes).intValue();
            //int id = ByteBuffer.wrap(idBytes).getInt();
            String name = new String(nameBytes, StandardCharsets.UTF_8);

            User user = new User();
            user.setId(id);
            user.setName(name);

            return user;
        }
        return null;
    }

    @Override
    public void close() {

    }
}

package com.nttdata.customer.serializer;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.customer.model.dto.Holder;
import com.nttdata.customer.model.dto.Signer;
import com.nttdata.customer.model.mongo.EmployeeMongo;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Class Product2JsonRedisSerializer.
 */
public class Employee2JsonRedisSerializer implements RedisSerializer<EmployeeMongo> {
  public static final Charset DEFAULT_CHARSET;

  static final byte[] EMPTY_ARRAY = new byte[0];

  static boolean isEmpty(byte[] data) {
    return data == null || data.length == 0;
  }

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public byte[] serialize(EmployeeMongo productMongo) throws SerializationException {
    if (productMongo == null) {
      return EMPTY_ARRAY;
    } else {
      try {
        return this.objectMapper.writeValueAsBytes(productMongo);
      } catch (Exception var3) {
        throw new SerializationException("Could not write JSON: "
                + var3.getMessage(), var3);
      }
    }
  }

  @Override
  public EmployeeMongo deserialize(byte[] bytes) throws SerializationException {
    if (isEmpty(bytes)) {
      return null;
    } else {
      try {
        JavaType type =  this.objectMapper.getTypeFactory().constructType(LinkedHashMap.class);
        LinkedHashMap<String, String> hashMap =
                this.objectMapper.readValue(bytes, 0, bytes.length, type);
        if (hashMap.get("type").equals(EmployeeMongo.EMPLOYEE_TYPE_1)) {
          type =  this.objectMapper.getTypeFactory().constructType(Holder.class);
        } else if (hashMap.get("type").equals(EmployeeMongo.EMPLOYEE_TYPE_2)) {
          type =  this.objectMapper.getTypeFactory().constructType(Signer.class);
        }
        return this.objectMapper.convertValue(hashMap, type);
      } catch (Exception var3) {
        throw new SerializationException("Could not read JSON: " + var3.getMessage(), var3);
      }
    }
  }

  static {
    DEFAULT_CHARSET = StandardCharsets.UTF_8;
  }

}

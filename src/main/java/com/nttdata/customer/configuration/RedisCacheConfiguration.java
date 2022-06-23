package com.nttdata.customer.configuration;

import com.nttdata.customer.model.mongo.CustomerMongo;
import com.nttdata.customer.model.mongo.EmployeeMongo;
import com.nttdata.customer.serializer.Customer2JsonRedisSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Class RedisCacheConfiguration.
 */
@Configuration
@ConditionalOnProperty(name = "cache.enabled", havingValue = "true")
public class RedisCacheConfiguration {

  /**
   * Method hashOperations.
   */
  @Bean
  public ReactiveHashOperations<String, String, CustomerMongo> hashOperations(
          ReactiveRedisConnectionFactory redisConnectionFactory) {
    return new ReactiveRedisTemplate<>(
            redisConnectionFactory,
            RedisSerializationContext
                    .<String, CustomerMongo>newSerializationContext(new StringRedisSerializer())
                    .hashKey(new GenericToStringSerializer<>(String.class))
                    .hashValue(new Customer2JsonRedisSerializer())
                    .build()
    ).opsForHash();
  }

  /**
   * Method hashOperationsEmployee.
   */
  @Bean
  public ReactiveHashOperations<String, String, EmployeeMongo> hashOperationsEmployee(
          ReactiveRedisConnectionFactory redisConnectionFactory) {
    return new ReactiveRedisTemplate<>(
            redisConnectionFactory,
            RedisSerializationContext
                    .<String, CustomerMongo>newSerializationContext(new StringRedisSerializer())
                    .hashKey(new GenericToStringSerializer<>(String.class))
                    .hashValue(new Customer2JsonRedisSerializer())
                    .build()
    ).opsForHash();
  }

}

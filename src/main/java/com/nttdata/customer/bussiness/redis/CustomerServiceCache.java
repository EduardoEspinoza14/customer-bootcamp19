package com.nttdata.customer.bussiness.redis;

import com.nttdata.customer.bussiness.impl.CustomerServiceImpl;
import com.nttdata.customer.model.mongo.CustomerMongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Class CustomerServiceCache.
 */
@Service
@ConditionalOnProperty(name = "cache.enabled", havingValue = "true")
public class CustomerServiceCache extends CustomerServiceImpl {

  private final Logger log = LoggerFactory.getLogger(CustomerServiceCache.class);

  private static final String KEY_CACHE = "customers";

  @Autowired
  private ReactiveHashOperations<String, String, CustomerMongo> hashOperations;

  @Override
  public Mono<CustomerMongo> getCustomer(String id) {
    return hashOperations.get(KEY_CACHE, id)
            .switchIfEmpty(this.getCustomerSaveCacheRedis(id));
  }

  @Override
  public Mono<CustomerMongo> insertCustomer(CustomerMongo customerMongo) {
    return super.insertCustomer(customerMongo)
            .flatMap(this::saveCacheRedis);
  }

  @Override
  public Mono<CustomerMongo> updateCustomer(CustomerMongo customer, String id) {
    return this.hashOperations.remove(KEY_CACHE, id)
            .then(super.updateCustomer(customer, id))
            .flatMap(this::saveCacheRedis);
  }

  @Override
  public Mono<Void> deleteCustomer(String id) {
    return this.hashOperations.remove(KEY_CACHE, id)
            .then(super.deleteCustomer(id));
  }

  private Mono<CustomerMongo> getCustomerSaveCacheRedis(String id) {
    return super.getCustomer(id)
            .flatMap(this::saveCacheRedis);
  }

  private Mono<CustomerMongo> saveCacheRedis(CustomerMongo customerMongo) {
    log.info("REDIS CACHE CUSTOMER: {}", customerMongo);
    return this.hashOperations.put(KEY_CACHE,
                    customerMongo.getId(),
                    customerMongo)
            .thenReturn(customerMongo);
  }

}

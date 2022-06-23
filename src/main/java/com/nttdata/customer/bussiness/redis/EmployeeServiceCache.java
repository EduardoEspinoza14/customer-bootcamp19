package com.nttdata.customer.bussiness.redis;

import com.nttdata.customer.bussiness.impl.EmployeeServiceImpl;
import com.nttdata.customer.model.mongo.EmployeeMongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Class EmployeeServiceCache.
 */
@Service
@ConditionalOnProperty(name = "cache.enabled", havingValue = "true")
public class EmployeeServiceCache extends EmployeeServiceImpl {

  private final Logger log = LoggerFactory.getLogger(CustomerServiceCache.class);

  private static final String KEY_CACHE = "employees";

  @Autowired
  private ReactiveHashOperations<String, String, EmployeeMongo> hashOperations;

  @Override
  public Mono<EmployeeMongo> getEmployee(String id) {
    return hashOperations.get(KEY_CACHE, id)
            .switchIfEmpty(this.getEmployeeSaveCacheRedis(id));
  }

  @Override
  public Mono<EmployeeMongo> insertEmployee(String companyId, EmployeeMongo employee) {
    return super.insertEmployee(companyId, employee)
            .flatMap(this::saveCacheRedis);
  }

  @Override
  public Mono<EmployeeMongo> updateEmployee(String companyId,
                                            EmployeeMongo employeeMongo,
                                            String id) {
    return this.hashOperations.remove(KEY_CACHE, id)
            .then(super.updateEmployee(companyId, employeeMongo, id))
            .flatMap(this::saveCacheRedis);
  }

  @Override
  public Mono<Void> deleteEmployee(String id) {
    return this.hashOperations.remove(KEY_CACHE, id)
            .then(super.deleteEmployee(id));
  }

  private Mono<EmployeeMongo> getEmployeeSaveCacheRedis(String id) {
    return super.getEmployee(id)
            .flatMap(this::saveCacheRedis);
  }

  private Mono<EmployeeMongo> saveCacheRedis(EmployeeMongo employeeMongo) {
    log.info("REDIS CACHE EMPLOYEE: {}", employeeMongo);
    return this.hashOperations.put(KEY_CACHE,
                    employeeMongo.getId(),
                    employeeMongo)
            .thenReturn(employeeMongo);
  }

}

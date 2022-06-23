package com.nttdata.customer.bussiness.impl;

import com.nttdata.customer.bussiness.CustomerService;
import com.nttdata.customer.configuration.KafkaProducerConfiguration;
import com.nttdata.customer.model.mongo.CustomerMongo;
import com.nttdata.customer.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderOptions;

/**
 * Class CustomerServiceImpl.
 */
@Service
@ConditionalOnProperty(name = "cache.enabled", havingValue = "false")
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private SenderOptions<String, CustomerMongo> senderOptions;

  @Override
  public Flux<CustomerMongo> getCustomers() {
    return customerRepository.findAll();
  }

  @Override
  public Flux<CustomerMongo> getCustomersByType(String type) {
    return customerRepository.findByType(type);
  }

  @Override
  public Mono<CustomerMongo> getCustomer(String id) {
    return customerRepository.findById(id);
  }

  @Override
  public Mono<CustomerMongo> insertCustomer(CustomerMongo customer) {
    return customerRepository.insert(customer)
            .doOnSuccess(customerMongo -> KafkaProducerConfiguration
                    .senderCreate(senderOptions,
                            KafkaProducerConfiguration.insertRecord(customerMongo))
                    .subscribe());
  }

  @Override
  public Mono<CustomerMongo> updateCustomer(CustomerMongo customer, String id) {
    return customerRepository.findById(id)
            .map(customerMongo -> {
              BeanUtils.copyProperties(customer, customerMongo, "id", "type");
              return customerMongo;
            })
            .flatMap(customerRepository::save)
            .doOnSuccess(customerMongo -> KafkaProducerConfiguration
                    .senderCreate(senderOptions,
                            KafkaProducerConfiguration.updateRecord(customerMongo))
                    .subscribe());
  }

  @Override
  public Mono<Void> deleteCustomer(String id) {
    return customerRepository.findById(id)
            .flatMap(c -> customerRepository.deleteById(c.getId()))
            .doOnSuccess(voidReturn -> KafkaProducerConfiguration
                    .senderCreate(senderOptions, KafkaProducerConfiguration.deleteRecord(id))
                    .subscribe());
  }
}

package com.nttdata.customer.bussiness.impl;

import com.nttdata.customer.bussiness.CustomerService;
import com.nttdata.customer.model.mongo.CustomerMongo;
import com.nttdata.customer.model.mongo.EmployeeMongo;
import com.nttdata.customer.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class CustomerServiceImpl.
 */
@Service
@Primary
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public Flux<CustomerMongo> getCustomers() {
    return customerRepository.findAll();
  }

  public Flux<CustomerMongo> getCustomersByType(String type) {
    return customerRepository.findByType(type);
  }

  @Override
  public Mono<CustomerMongo> getCustomer(String id) {
    return customerRepository.findById(id);
  }

  @Override
  public Mono<CustomerMongo> insertCustomer(CustomerMongo customer) {
    return customerRepository.insert(customer);
  }

  @Override
  public Mono<CustomerMongo> updateCustomer(CustomerMongo customerMongo, String id) {
    return customerRepository.findById(id)
            .map(customer -> {
              BeanUtils.copyProperties(customerMongo, customer, "id", "type");
              return customer;
            })
            .flatMap(customerRepository::save);
  }

  @Override
  public Mono<Void> deleteCustomer(String id) {
    return customerRepository.findById(id)
            .flatMap(c -> customerRepository.deleteById(c.getId()));
  }
}

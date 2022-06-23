package com.nttdata.customer.bussiness.service;

import com.nttdata.customer.bussiness.CustomerService;
import com.nttdata.customer.model.dto.Person;
import com.nttdata.customer.model.mongo.CustomerMongo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class PersonService.
 */
@Service
public class PersonService {

  private final CustomerService customerService;

  public PersonService(CustomerService customerService) {
    this.customerService = customerService;
  }

  public Flux<CustomerMongo> getCustomers() {
    return this.customerService.getCustomersByType(CustomerMongo.CUSTOMER_TYPE_1);
  }

  public Mono<CustomerMongo> getCustomer(String id) {
    return this.customerService.getCustomer(id);
  }

  public Mono<CustomerMongo> insertCustomer(Person person) {
    //DEBERIA VALIDAR
    return this.customerService.insertCustomer(person);
  }

  public Mono<CustomerMongo> updateCustomer(Person person, String id) {
    //DEBERIA VALIDAR
    return this.customerService.updateCustomer(person, id);
  }

  public Mono<Void> deleteCustomer(String id) {
    return this.customerService.deleteCustomer(id);
  }

}

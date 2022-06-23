package com.nttdata.customer.bussiness.service;

import com.nttdata.customer.bussiness.CustomerService;
import com.nttdata.customer.bussiness.impl.CustomerServiceImpl;
import com.nttdata.customer.model.dto.Company;
import com.nttdata.customer.model.mongo.CustomerMongo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class CompanyService.
 */
@Service
public class CompanyService {

  private final CustomerService customerService;

  public CompanyService(CustomerService customerService) {
    this.customerService = customerService;
  }

  public Flux<CustomerMongo> getCustomers() {
    return this.customerService.getCustomersByType(CustomerMongo.CUSTOMER_TYPE_2);
  }

  public Mono<CustomerMongo> getCustomer(String id) {
    return this.customerService.getCustomer(id);
  }

  public Mono<CustomerMongo> insertCustomer(Company company) {
    //DEBERIA VALIDAR
    return this.customerService.insertCustomer(company);
  }

  public Mono<CustomerMongo> updateCustomer(Company company, String id) {
    //DEBERIA VALIDAR
    return this.customerService.updateCustomer(company, id);
  }

  public Mono<Void> deleteCustomer(String id) {
    return this.customerService.deleteCustomer(id);
  }

}

package com.nttdata.customer.bussiness.service;

import com.nttdata.customer.bussiness.impl.CustomerServiceImpl;
import com.nttdata.customer.model.mongo.CustomerMongo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * Class PersonService.
 */
@Service
public class PersonService extends CustomerServiceImpl {

  @Override
  public Flux<CustomerMongo> getCustomers() {
    return getCustomersByType(CustomerMongo.CUSTOMER_TYPE_1);
  }

}

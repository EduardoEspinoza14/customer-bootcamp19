package com.nttdata.customer.bussiness;

import com.nttdata.customer.model.mongo.CustomerMongo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Flux<CustomerMongo> getCustomers();

    Mono<CustomerMongo> getCustomer(String id);

    Mono<CustomerMongo> insertCustomer(CustomerMongo customer);

    Mono<CustomerMongo> updateCustomer(CustomerMongo customer, String id);

    Mono<Void> deleteCustomer(String id);

}

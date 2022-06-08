package com.nttdata.customer.bussiness.impl;

import com.nttdata.customer.bussiness.CustomerService;
import com.nttdata.customer.model.mongo.CustomerMongo;
import com.nttdata.customer.model.mongo.EmployeeMongo;
import com.nttdata.customer.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Flux<CustomerMongo> getCustomers() {
        return customerRepository.findAll();
    }

    public Flux<CustomerMongo> getCustomersByType(String type){
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
    public Mono<CustomerMongo> updateCustomer(CustomerMongo customer, String id) {
        return customerRepository.findById(id)
                .map(c_db -> {
                    BeanUtils.copyProperties(customer, c_db, "id", "type");
                    return c_db;
                })
                .flatMap(customerRepository::save);
    }

    @Override
    public Mono<Void> deleteCustomer(String id) {
        return customerRepository.findById(id)
                .flatMap(c->customerRepository.deleteById(c.getId()));
    }
}

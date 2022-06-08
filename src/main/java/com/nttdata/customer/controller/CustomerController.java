package com.nttdata.customer.controller;

import com.nttdata.customer.bussiness.CustomerService;
import com.nttdata.customer.model.mongo.CustomerMongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class CustomerController {

    private final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CustomerMongo> getCustomer(@PathVariable String id) {
        log.info("CONSUMING SERVICE GET CUSTOMER BY ID");
        return service.getCustomer(id);
    }

}

package com.nttdata.customer.controller;

import com.nttdata.customer.bussiness.service.CompanyService;
import com.nttdata.customer.model.dto.Company;
import com.nttdata.customer.model.mongo.CustomerMongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class CompanyController.
 */
@RestController
@RequestMapping("/company")
@CrossOrigin(origins = "*", methods = {
    RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE
})
public class CompanyController {

  private final Logger log = LoggerFactory.getLogger(CompanyController.class);

  @Autowired
  CompanyService service;

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<CustomerMongo> getAllCompanies() {
    return service.getCustomers();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<CustomerMongo> getCompany(@PathVariable String id) {
    return service.getCustomer(id);
  }

  @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<CustomerMongo> createCompany(@RequestBody Company customer) {
    return service.insertCustomer(customer);
  }

  @PostMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<CustomerMongo> modifyCompany(@RequestBody Company customer, @PathVariable String id) {
    return service.updateCustomer(customer, id);
  }

  @PostMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Void> removeCompany(@PathVariable String id) {
    return service.deleteCustomer(id);
  }

}

package com.nttdata.customer.controller;

import com.nttdata.customer.bussiness.EmployeeService;
import com.nttdata.customer.model.dto.Holder;
import com.nttdata.customer.model.mongo.EmployeeMongo;
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
 * Class EmployeeController.
 */
@RestController
@RequestMapping("/employee/{customerId}")
@CrossOrigin(origins = "*", methods = {
    RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE
})
public class EmployeeController {

  private final Logger log = LoggerFactory.getLogger(EmployeeController.class);

  @Autowired
  EmployeeService service;

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<EmployeeMongo> getEmployeeByCompany(@PathVariable String customerId) {
    return service.getEmployeesByCompany(customerId);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<EmployeeMongo> getEmployee(@PathVariable String customerId, @PathVariable String id) {
    return service.getEmployee(customerId, id);
  }

}

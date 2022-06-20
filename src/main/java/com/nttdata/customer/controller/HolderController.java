package com.nttdata.customer.controller;

import com.nttdata.customer.bussiness.service.HolderService;
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
 * Class HolderController.
 */
@RestController
@RequestMapping("/holder/{companyId}")
@CrossOrigin(origins = "*", methods = {
    RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE
})
public class HolderController {

  private final Logger log = LoggerFactory.getLogger(HolderController.class);

  @Autowired
  HolderService service;

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<EmployeeMongo> getAllHolders(@PathVariable String companyId) {
    return service.getEmployeesByCompany(companyId);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<EmployeeMongo> getHolder(@PathVariable String companyId, @PathVariable String id) {
    return service.getEmployee(id);
  }

  @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<EmployeeMongo> createHolder(@PathVariable String companyId,
                                          @RequestBody Holder employee) {
    return service.insertEmployee(companyId, employee);
  }

  @PostMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<EmployeeMongo> modifyHolder(@PathVariable String companyId,
                                          @RequestBody Holder employee,
                                          @PathVariable String id) {
    return service.updateEmployee(companyId, employee, id);
  }

  @PostMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Void> removeHolder(@PathVariable String companyId, @PathVariable String id) {
    return service.deleteEmployee(id);
  }

}

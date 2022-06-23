package com.nttdata.customer.bussiness.service;

import com.nttdata.customer.bussiness.EmployeeService;
import com.nttdata.customer.model.dto.Holder;
import com.nttdata.customer.model.mongo.EmployeeMongo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class HolderService.
 */
@Service
public class HolderService {

  private final EmployeeService employeeService;

  public HolderService(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  public Flux<EmployeeMongo> getEmployeesByCompany(String companyId) {
    return this.employeeService.getEmployeesByCompany(companyId, EmployeeMongo.EMPLOYEE_TYPE_1);
  }

  public Mono<EmployeeMongo> getEmployee(String id) {
    return this.employeeService.getEmployee(id);
  }

  public Mono<EmployeeMongo> insertEmployee(String companyId, Holder holder) {
    //DEBERIA VALIDAR
    return this.employeeService.insertEmployee(companyId, holder);
  }

  public Mono<EmployeeMongo> updateEmployee(String companyId, Holder holder, String id) {
    //DEBERIA VALIDAR
    return this.employeeService.updateEmployee(companyId, holder, id);
  }

  public Mono<Void> deleteEmployee(String id) {
    return this.employeeService.deleteEmployee(id);
  }

}

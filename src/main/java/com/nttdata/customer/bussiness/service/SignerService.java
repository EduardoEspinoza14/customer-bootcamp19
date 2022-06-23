package com.nttdata.customer.bussiness.service;

import com.nttdata.customer.bussiness.EmployeeService;
import com.nttdata.customer.model.dto.Signer;
import com.nttdata.customer.model.mongo.EmployeeMongo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class SignerService.
 */
@Service
public class SignerService {

  private final EmployeeService employeeService;

  public SignerService(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  public Flux<EmployeeMongo> getEmployeesByCompany(String companyId) {
    return this.employeeService.getEmployeesByCompany(companyId, EmployeeMongo.EMPLOYEE_TYPE_2);
  }

  public Mono<EmployeeMongo> getEmployee(String id) {
    return this.employeeService.getEmployee(id);
  }

  public Mono<EmployeeMongo> insertEmployee(String companyId, Signer signer) {
    //DEBERIA VALIDAR
    return this.employeeService.insertEmployee(companyId, signer);
  }

  public Mono<EmployeeMongo> updateEmployee(String companyId, Signer signer, String id) {
    //DEBERIA VALIDAR
    return this.employeeService.updateEmployee(companyId, signer, id);
  }

  public Mono<Void> deleteEmployee(String id) {
    return this.employeeService.deleteEmployee(id);
  }

}

package com.nttdata.customer.bussiness.impl;

import com.nttdata.customer.bussiness.EmployeeService;
import com.nttdata.customer.model.mongo.EmployeeMongo;
import com.nttdata.customer.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class EmployeeServiceImpl.
 */
@Service
@ConditionalOnProperty(name = "cache.enabled", havingValue = "false", matchIfMissing = true)
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Override
  public Flux<EmployeeMongo> getEmployeesByCompany(String companyId) {
    return employeeRepository.findByCompanyId(companyId);
  }

  @Override
  public Flux<EmployeeMongo> getEmployeesByCompany(String companyId, String type) {
    return employeeRepository.findByCompanyIdAndType(companyId, type);
  }

  @Override
  public Mono<EmployeeMongo> getEmployee(String id) {
    return employeeRepository.findById(id);
  }

  @Override
  public Mono<EmployeeMongo> getEmployee(String companyId, String id) {
    return employeeRepository.findByCompanyIdAndId(companyId, id);
  }

  @Override
  public Mono<EmployeeMongo> insertEmployee(String companyId, EmployeeMongo employee) {
    employee.setCompanyId(companyId);
    return employeeRepository.insert(employee);
  }

  @Override
  public Mono<EmployeeMongo> updateEmployee(String companyId,
                                            EmployeeMongo employeeMongo,
                                            String id) {
    employeeMongo.setCompanyId(companyId);
    return employeeRepository.findById(id)
            .map(employee -> {
              BeanUtils.copyProperties(employeeMongo, employee, "id", "type");
              return employee;
            })
            .flatMap(employeeRepository::save);
  }

  @Override
  public Mono<Void> deleteEmployee(String id) {
    return employeeRepository.findById(id)
            .flatMap(e -> employeeRepository.deleteById(e.getId()));
  }

}

package com.nttdata.customer.bussiness;

import com.nttdata.customer.model.mongo.EmployeeMongo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {

    Flux<EmployeeMongo> getEmployeesByCompany(String companyId);

    Mono<EmployeeMongo> getEmployee(String id);

    Mono<EmployeeMongo> insertEmployee(String companyId, EmployeeMongo employee);

    Mono<EmployeeMongo> updateEmployee(String companyId, EmployeeMongo employee, String id);

    Mono<Void> deleteEmployee(String id);

}

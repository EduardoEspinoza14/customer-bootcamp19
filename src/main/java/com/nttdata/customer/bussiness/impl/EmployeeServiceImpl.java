package com.nttdata.customer.bussiness.impl;

import com.nttdata.customer.bussiness.EmployeeService;
import com.nttdata.customer.model.mongo.EmployeeMongo;
import com.nttdata.customer.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Flux<EmployeeMongo> getEmployees() {
        return employeeRepository.findAll();
    }

    public abstract Flux<EmployeeMongo> getEmployeesByCompany(String companyId);

    public Flux<EmployeeMongo> getEmployeesByCompany(String companyId, String type){
        return employeeRepository.findByCompanyIdAndType(companyId, type);
    }

    @Override
    public Mono<EmployeeMongo> getEmployee(String id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Mono<EmployeeMongo> insertEmployee(String companyId, EmployeeMongo employee) {
        employee.setCompanyId(companyId);
        return employeeRepository.insert(employee);
    }

    @Override
    public Mono<EmployeeMongo> updateEmployee(String companyId, EmployeeMongo employee, String id) {
        employee.setCompanyId(companyId);
        return employeeRepository.findById(id)
                .map(e_db -> {
                    BeanUtils.copyProperties(employee, e_db, "id", "type");
                    return e_db;
                })
                .flatMap(employeeRepository::save);
    }

    @Override
    public Mono<Void> deleteEmployee(String id) {
        return employeeRepository.findById(id)
                .flatMap(e->employeeRepository.deleteById(e.getId()));
    }
}

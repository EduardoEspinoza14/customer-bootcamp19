package com.nttdata.customer.bussiness.service;

import com.nttdata.customer.bussiness.impl.EmployeeServiceImpl;
import com.nttdata.customer.model.mongo.EmployeeMongo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class HolderService extends EmployeeServiceImpl {

    @Override
    public Flux<EmployeeMongo> getEmployeesByCompany(String companyId) {
        return getEmployeesByCompany(companyId, EmployeeMongo.EMPLOYEE_TYPE_1);
    }

}

package com.nttdata.customer.repository;

import com.nttdata.customer.model.mongo.EmployeeMongo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<EmployeeMongo, String> {

    Flux<EmployeeMongo> findByCompanyId(String CompanyId);

    Flux<EmployeeMongo> findByCompanyIdAndType(String CompanyId, String type);

}

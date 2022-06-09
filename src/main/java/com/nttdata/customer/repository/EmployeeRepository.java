package com.nttdata.customer.repository;

import com.nttdata.customer.model.mongo.EmployeeMongo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<EmployeeMongo, String> {

    Flux<EmployeeMongo> findByCompanyId(String CompanyId);

    Mono<EmployeeMongo> findByCompanyIdAndId(String CompanyId, String id);

    Flux<EmployeeMongo> findByCompanyIdAndType(String CompanyId, String type);

}

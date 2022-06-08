package com.nttdata.customer.repository;

import com.nttdata.customer.model.mongo.CustomerMongo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<CustomerMongo, String> {

    Flux<CustomerMongo> findByType(String type);

}

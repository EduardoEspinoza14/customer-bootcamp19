package com.nttdata.customer.bussiness;

import com.nttdata.customer.model.mongo.CustomerMongo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interface CustomerService.
 */
public interface CustomerService {

  //PARA OBTENER TODOS LOS CLIENTES DEL BANCO
  Flux<CustomerMongo> getCustomers();

  //PARA OBTENER CLIENTE POR ID
  Mono<CustomerMongo> getCustomer(String id);

  //PARA INSERTAR CLIENTE
  Mono<CustomerMongo> insertCustomer(CustomerMongo customer);

  //PARA ACTUALIZAR CLIENTE POR ID
  Mono<CustomerMongo> updateCustomer(CustomerMongo customer, String id);

  //PARA ELIMINAR CLIENTE POR ID
  Mono<Void> deleteCustomer(String id);

}

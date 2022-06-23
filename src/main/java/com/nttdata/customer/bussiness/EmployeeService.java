package com.nttdata.customer.bussiness;

import com.nttdata.customer.model.mongo.EmployeeMongo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class EmployeeService.
 */
public interface EmployeeService {

  //PARA BUSCAR EMPLEADOS POR EL ID DEL CLIENTE DEL TIPO EMPRESA (COMPANY)
  Flux<EmployeeMongo> getEmployeesByCompany(String companyId);

  Flux<EmployeeMongo> getEmployeesByCompany(String companyId, String type);

  //PARA OBTENER UN SOLO EMPLEADO POR SU ID
  Mono<EmployeeMongo> getEmployee(String id);

  //PARA OBTENER UN SOLO EMPLEADO POR SU ID Y ID DE CLIENTE (COMPANY),
  //PARA VERIFICAR QUE EL EMPLEADO CONSULTADO PERTENECE AL CLIENTE
  Mono<EmployeeMongo> getEmployee(String companyId, String id);

  //PARA INSERTAR UN EMPLEADO A UN CLIENTE TIPO EMPRESA, REFERENCIADO POR EL ID DEL CLIENTE
  Mono<EmployeeMongo> insertEmployee(String companyId, EmployeeMongo employee);

  //PARA ACTUALIZAR UN EMPLEADO DE UN CLIENTE TIPO EMPRESA,
  //REFERENCIADO POR EL ID DEL CLIENTE E ID DEL EMPLEADO
  Mono<EmployeeMongo> updateEmployee(String companyId, EmployeeMongo employee, String id);

  //PARA ELIMINAR EMPLEADO POR ID
  Mono<Void> deleteEmployee(String id);

}

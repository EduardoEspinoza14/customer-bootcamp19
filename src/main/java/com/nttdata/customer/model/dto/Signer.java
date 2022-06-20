package com.nttdata.customer.model.dto;

import com.nttdata.customer.model.mongo.EmployeeMongo;
import lombok.Getter;
import lombok.Setter;

/**
 * Class Signer.
 */
//SE REFIERE A LOS TRABAJADORES QUE SON FIRMANTES, EXTIENDE DE LA CLASE PRINCIPAL DE EMPLOYEE
@Getter
@Setter
public class Signer extends EmployeeMongo {

  public Signer() {
    super(EMPLOYEE_TYPE_2);
  }

}

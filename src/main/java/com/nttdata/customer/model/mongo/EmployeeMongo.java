package com.nttdata.customer.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class EmployeeMongo.
 * SE REFIERE A LOS TRABAJADORES O RELACIONADOS QUE PUEDE TENER UN CLIENTE DEL TIPO EMPRESA,
 * QUIENES MANEJAN SUS CUENTAS, PUEDEN SER FIRMANTES O TITULARES
 */
@Data
@Document(collection = "employees")
public class EmployeeMongo {

  public static String EMPLOYEE_TYPE_1 = "Holder";
  public static String EMPLOYEE_TYPE_2 = "Signer";

  public EmployeeMongo(String type) {
    setType(type);
  }

  @Id
  private String id;
  private String name;
  private String lastName;
  private String type;
  private String companyId;

  @Override
  public String toString() {
    return "{id: " + getId() +
            ", name: " + getName() +
            ", lastName: " + getLastName() +
            ", type: " + getType() +
            "}";
  }

}

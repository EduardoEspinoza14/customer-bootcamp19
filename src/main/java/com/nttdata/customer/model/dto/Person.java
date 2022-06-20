package com.nttdata.customer.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nttdata.customer.model.mongo.CustomerMongo;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;


/**
 * Class Person.
 * SE REFIERE A LOS CLIENTES QUE SON PERSONAS, POR TANTO EXTIENDE DE LA CLASE CLIENTE PRINCIPAL
 */
@Getter
@Setter
public class Person extends CustomerMongo {

  private String lastName;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date dateBorn;
  private String dni;

  public Person() {
    super(CUSTOMER_TYPE_1);
  }

  @Override
  public String toString() {
    return "{id: " + getId()
            + ", name: " + getName()
            + ", address: " + getAddress()
            + ", phone: " + getPhone()
            + ", type: " + getType()
            + ", lastName: " + getLastName()
            + ", dateBorn: " + getDateBorn()
            + ", dni: " + getDni()
            + "}";
  }

}

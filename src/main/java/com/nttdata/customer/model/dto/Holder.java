package com.nttdata.customer.model.dto;

import com.nttdata.customer.model.mongo.EmployeeMongo;
import lombok.Getter;
import lombok.Setter;

//SE REFIERE A LOS TRABAJADORES DEL CLIENTE QUE SON TITULARES, EXTIENDE DE LA CLASE PRINCIPAL EMPLOYEE(TRABAJADOR)
@Getter
@Setter
public class Holder extends EmployeeMongo {

    public Holder() {
        super(EMPLOYEE_TYPE_1);
    }

}

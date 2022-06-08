package com.nttdata.customer.model.dto;

import com.nttdata.customer.model.mongo.EmployeeMongo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Signer extends EmployeeMongo {

    public Signer() {
        super(EMPLOYEE_TYPE_2);
    }

}

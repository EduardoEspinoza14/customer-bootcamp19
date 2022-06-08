package com.nttdata.customer.model.dto;

import com.nttdata.customer.model.mongo.EmployeeMongo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Holder extends EmployeeMongo {

    public Holder() {
        super(EMPLOYEE_TYPE_1);
    }

}

package com.nttdata.customer.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nttdata.customer.model.mongo.CustomerMongo;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

//SE REFIERE A LOS CLIENTES QUE SON PERSONAS, POR TANTO EXTIENDE DE LA CLASE CLIENTE PRINCIPAL
@Getter
@Setter
public class Person extends CustomerMongo {

    private String last_name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date_born;
    private String DNI;

    public Person() {
        super(CUSTOMER_TYPE_1);
    }

    @Override
    public String toString(){
        return "{id: " + getId() +
                ", name: " + getName() +
                ", address: " + getAddress() +
                ", phone: " + getPhone() +
                ", type: " + getType() +
                ", last_name: " + getLast_name() +
                ", date_born: " + getDate_born() +
                ", DNI: " + getDNI() +
                "}";
    }

}

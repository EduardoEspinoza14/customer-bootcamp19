package com.nttdata.customer.model.dto;

import com.nttdata.customer.model.mongo.CustomerMongo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

//SE REFIERE A LOS CLIENTES QUE SON EMPRESAS, POR TANTO EXTIENDE DE LA CLASE CLIENTE PRINCIPAL
@Getter
@Setter
public class Company extends CustomerMongo {

    private String RUC;

    @DBRef
    private List<Holder> holders;
    @DBRef
    private List<Signer> signers;

    public Company() {
        super(CUSTOMER_TYPE_2);
    }

    @Override
    public String toString(){
        return "{id: " + getId() +
                ", name: " + getName() +
                ", address: " + getAddress() +
                ", phone: " + getPhone() +
                ", type: " + getType() +
                ", RUC: " + getRUC() +
                "}";
    }

}

package com.nttdata.customer.model.mongo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nttdata.customer.model.extdto.Product;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "customers")
public abstract class CustomerMongo {

    public static String CUSTOMER_TYPE_1 = "Person";
    public static String CUSTOMER_TYPE_2 = "Company";

    public CustomerMongo(String type){
        setType(type);
    }

    @Id
    private String id;
    private String name;
    private String address;
    private String phone;
    private String type;

    @Transient
    private List<Product> products;

    public abstract String getCompleteName();

}

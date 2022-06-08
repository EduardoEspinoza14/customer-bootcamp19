package com.nttdata.customer.model.mongo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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

    public abstract String getCompleteName();

}

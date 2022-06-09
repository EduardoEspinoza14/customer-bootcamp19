package com.nttdata.customer.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//SE REFIERE AL CLIENTE EN GENERAL, QUE PUEDE SER PERSONA O EMPRESA, SE DEFINEN CONSTANTES PARA LA DIFERENCIACION
@Data
@Document(collection = "customers")
public class CustomerMongo {

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

}

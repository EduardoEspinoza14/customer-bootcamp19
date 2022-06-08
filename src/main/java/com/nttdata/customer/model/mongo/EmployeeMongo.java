package com.nttdata.customer.model.mongo;

import com.nttdata.customer.model.dto.Company;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "employees")
public class EmployeeMongo {

    public static String EMPLOYEE_TYPE_1 = "Holder";
    public static String EMPLOYEE_TYPE_2 = "Signer";

    public EmployeeMongo(String type){
        setType(type);
    }

    @Id
    private String id;
    private String name;
    private String last_name;
    private String type;
    private String companyId;

    @Override
    public String toString(){
        return "{id: " + getId() +
                ", name: " + getName() +
                ", last_name: " + getLast_name() +
                ", type: " + getType() +
                "}";
    }

}

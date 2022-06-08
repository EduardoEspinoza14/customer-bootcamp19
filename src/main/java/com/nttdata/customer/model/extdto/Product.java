package com.nttdata.customer.model.extdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String id;
    private Date start_date;
    private String number;
    private String type;
    private Double credit_limit;
    private Date expiration_date;
    private String security_code;
    protected Double commission_amount;
    private Integer single_day_movement;
    private Double credit_amount;
    private Integer payment_day;
    private Integer max_movement_limit;

}

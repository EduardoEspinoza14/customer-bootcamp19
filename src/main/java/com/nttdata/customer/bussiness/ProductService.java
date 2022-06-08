package com.nttdata.customer.bussiness;

import com.nttdata.customer.model.extdto.Product;
import reactor.core.publisher.Flux;

public interface ProductService {

    public Flux<Product> getProductsByCustomer(String customerId);

}

package com.nttdata.customer.bussiness.impl;

import com.nttdata.customer.bussiness.ProductService;
import com.nttdata.customer.model.extdto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    WebClient.Builder webClientBuilder;

    @Override
    public Flux<Product> getProductsByCustomer(String customerId) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/product/{customerId}", customerId)
                .retrieve()
                .bodyToFlux(Product.class);
    }
}

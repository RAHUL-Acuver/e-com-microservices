package com.ecom.order.clients;

import com.ecom.order.dtos.ProductResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("http://localhost:8081")
public interface ProductServiceClient {

    @GetExchange("/api/products/{id}")
    ProductResponse getProductDetails(@PathVariable Long id);

}

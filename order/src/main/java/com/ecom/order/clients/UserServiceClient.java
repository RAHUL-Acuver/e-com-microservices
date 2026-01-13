package com.ecom.order.clients;

import com.ecom.order.dtos.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("http://localhost:8082")
public interface UserServiceClient {

    @GetExchange("/users/api/get/{id}")
    UserResponse getUserDetails(@PathVariable("id") String id);

}

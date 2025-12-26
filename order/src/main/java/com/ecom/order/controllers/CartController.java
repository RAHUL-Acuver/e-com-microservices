package com.ecom.order.controllers;

import com.ecom.order.dtos.CartItemRequest;
import com.ecom.order.models.CartItem;
import com.ecom.order.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(
            @RequestHeader("X-User-ID") String userId,
            @RequestBody CartItemRequest request
                                              ) {

        if(!cartService.addToCart(userId,request)){
            return ResponseEntity.badRequest().body("Product out of stock or user not found or product not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> removeFromCart(
            @RequestHeader("X-User-ID") String userId,
            @PathVariable String productId
    ){
        boolean deleted= cartService.deleteItemFromCart(userId,productId);
        return deleted?ResponseEntity.noContent().build():ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/fetchCart")
    public ResponseEntity<List<CartItem>> getCartItems(@RequestHeader("X-User-ID") String userId) {
        return ResponseEntity.ok(cartService.fetchCartDetails(userId));
    }

}

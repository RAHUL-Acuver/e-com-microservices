package com.ecom.order.services;

//import com.ecom.order.clients.ProductServiceClient;
import com.ecom.order.dtos.CartItemRequest;
import com.ecom.order.dtos.ProductResponse;
import com.ecom.order.models.CartItem;
//import com.example.model.Product;
//import com.example.model.User;
import com.ecom.order.repositories.CartItemRepository;
//import com.example.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

   // private final ProductRepository productRepository;

    private final CartItemRepository cartItemRepository;

   // private final ProductServiceClient productServiceClient;

    //private final UserRepository userRepository;

    public boolean addToCart(String userId, CartItemRequest request) {
        //ProductResponse  productResponse=productServiceClient.getProductDetails(request.getProductId());

//        Optional<Product> productOpt = productRepository.findById(request.getProductId());
//        if (productOpt.isEmpty())
//            return false;

//        Product product = productOpt.get();
//        if (product.getQuantity() < request.getQuantity())
//            return false;

//        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
//        if (userOpt.isEmpty())
//            return false;

//        User user = userOpt.get();

        CartItem existing = cartItemRepository.findByUserIdAndProductId(userId, request.getProductId());

        if (existing != null) {
            //update the quantity
            existing.setQuantity(existing.getQuantity() + request.getQuantity());
            existing.setPrice(BigDecimal.valueOf(1000.00));

            cartItemRepository.save(existing);
        }
        else
            {
                CartItem cartItem = new CartItem();
                cartItem.setUserId(userId);
                cartItem.setProductId(String.valueOf(request.getProductId()));
                cartItem.setQuantity(request.getQuantity());
                cartItem.setPrice(BigDecimal.valueOf(1000.00));
                cartItemRepository.save(cartItem);
            }
           return true;
        }

    public boolean deleteItemFromCart(String userId, String productId) {
       CartItem cartItem= cartItemRepository.findByUserIdAndProductId(userId,productId);

        if (cartItem != null) {
            cartItemRepository.delete(cartItem);
            return true;
        }
       return false;
    }

//    public List<CartItem> fetchCartDetails(String userId) {
//
//        return cartItemRepository.findById(Long.valueOf(userId))
//                .stream().toList();
//
//    }

    public List<CartItem> fetchCartDetails(String userId) {
        return cartItemRepository.findByUserId(userId);
    }



    public void clearCart(String userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}

package com.ecom.order.repositories;

import com.ecom.order.models.CartItem;
//import com.example.model.Product;
//import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserIdAndProductId(String userId, String productId);

    void deleteByUserIdAndProductId(String userId, String productId);

    List<CartItem> findByUserId(Long aLong);

    List<CartItem> findByUserId( String userId);

    void deleteByUserId(String userId);
}

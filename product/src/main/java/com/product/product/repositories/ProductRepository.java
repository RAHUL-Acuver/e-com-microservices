package com.product.product.repositories;

import com.product.product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByActiveTrue();

    @Query("select p from Product p where p.active=true AND p.quantity>0 AND LOWER(p.name) LIKE LOWER(CONCAT('%',:keyword,'%'))")
    List<Product> searchProducts(@Param("keyword") String keyword);
}

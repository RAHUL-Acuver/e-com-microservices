//package com.example.service;
//
//import com.example.dto.OrderItemDto;
//import com.example.dto.OrderResponse;
//import com.example.model.*;
//import com.example.repository.OrderRepository;
//import com.example.repository.UserRepository;
//import com.example.service.CartService;
//import com.example.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class OrderService {
//
//    private final CartService cartService;
//    private final UserService userService;
//    private final UserRepository userRepository;
//    private final OrderRepository orderRepository;
//
//    public Optional<OrderResponse> createOrder(String userId){
//          List<CartItem> cartItems=cartService.fetchCartDetails(userId);
//          if(cartItems.isEmpty()){
//              return Optional.empty();
//          }
//
//          Optional<User> userOptional=userRepository.findById(Long.valueOf(userId));
//          if(userOptional.isEmpty()){
//              return Optional.empty();
//          }
//          User user=userOptional.get();
//
//          //calculate the price
//          BigDecimal totalPrice=cartItems.stream()
//                  .map(CartItem::getPrice)
//                  .reduce(BigDecimal.ZERO,BigDecimal::add);
//
//          //Create order
//        Order order=new Order();
//        order.setUser(user);
//        order.setStatus(OrderStatus.CONFIRMED);
//        order.setTotalAmount(totalPrice);
//
//        List<OrderItem> orderItems=cartItems.stream()
//                .map(item -> new OrderItem(
//                        null,
//                        item.getProduct(),
//                        item.getQuantity(),
//                        item.getPrice(),
//                        order
//                ))
//                .toList();
//
//        order.setItems(orderItems);
//        Order savedOrder=orderRepository.save(order);
//        cartService.clearCart(userId);
//        return Optional.of(mapToOrderResponse(savedOrder));
//    }
//
//    private OrderResponse mapToOrderResponse(Order order) {
//        return new OrderResponse(
//                order.getId(),
//                order.getTotalAmount(),
//                order.getStatus(),
//                order.getItems().stream()
//                        .map(orderItem -> new OrderItemDto(
//                                orderItem.getId(),
//                                orderItem.getProduct().getId(),
//                                orderItem.getQuantity(),
//                                orderItem.getPrice(),
//                                orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()))
//                        ))
//                        .toList()
//                ,order.getCreatedAt()
//        );
//    }}
//}
//-------------------------------------------------------------------------------------
package com.ecom.order.services;

import com.ecom.order.models.CartItem;
import com.ecom.order.models.Order;
import com.ecom.order.models.OrderItem;
import com.ecom.order.models.OrderStatus;
import com.ecom.order.dtos.OrderItemDto;
import com.ecom.order.dtos.OrderResponse;
//import com.example.model.*;
import com.ecom.order.repositories.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final CartService cartService;

    private final OrderRepository orderRepository;

    public Optional<OrderResponse> createOrder(String userId) {
        List<CartItem> cartItems=cartService.fetchCartDetails(userId);
        if(cartItems.isEmpty()){
            return Optional.empty();
        }
//        Optional<User> userOptional=userRepository.findById(Long.valueOf(userId));
//        if(userOptional.isEmpty()){
//            return Optional.empty();
//        }
//        User user=userOptional.get();

        //calculate total price
        BigDecimal totalPrice=cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order=new Order();
        order.setUserId(userId);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(totalPrice);

        List<OrderItem> orderItems=cartItems.stream()
                .map(item->new OrderItem(
                        null,
                        item.getProductId(),
                        item.getQuantity(),
                        item.getPrice(),
                        order
                ))
                .toList();

        order.setItems(orderItems);
        Order savedOrder=orderRepository.save(order);
        cartService.clearCart(userId);

        return Optional.of(mapToOrderResponse(savedOrder));

    }

    private OrderResponse mapToOrderResponse(Order order) {
        return  new OrderResponse(
                order.getId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getItems().stream()
                        .map(orderItem -> new OrderItemDto(
                                orderItem.getId(),
                                orderItem.getProductId(),
                                orderItem.getQuantity(),
                                orderItem.getPrice(),
                                orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()))
                        ))
                        .toList(),
                order.getCreatedAt()
        );
    }
}



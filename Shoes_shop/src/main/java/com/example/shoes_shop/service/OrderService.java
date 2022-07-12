package com.example.shoes_shop.service;

import com.example.shoes_shop.entity.Order;
import com.example.shoes_shop.entity.OrderDetail;
import com.example.shoes_shop.entity.Product;
import com.example.shoes_shop.repository.OrderRepository;
import com.example.shoes_shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {
    final OrderRepository orderRepository;

    final ProductRepository productRepository;
    public Order findShoppingCartByUserId(int userId){
        return orderRepository.getShoppingCart(userId);
    }

    public Order addShoppingCart(int userId, String productId, int quantity) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) {
            return null;
        }
        Order order = orderRepository.getShoppingCart(userId);
        Set<OrderDetail> orderDetails = order.getOrderDetails();
        boolean exist = false;
        for (OrderDetail entry : orderDetails) {
            if (entry.getProduct().getId().equals(productId)) {
                entry.setQuantity(entry.getQuantity() + quantity);
                exist = true;
            }
        }
        if (!exist) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetails.add(orderDetail);
        }
        //save
        return order;
    }
}

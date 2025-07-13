package com.retail.billingSystem.ServiceLayer;

import com.retail.billingSystem.Model.OrderEntity;
import com.retail.billingSystem.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderEntity placeOrder(OrderEntity order)
    {
        order.setCreatedAt(new Date());
        return orderRepository.save(order);
    }

    public List<OrderEntity> getAllOrders()
    {
        return orderRepository.findAll();
    }
}

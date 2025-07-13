package com.retail.billingSystem.Controller;


import com.retail.billingSystem.Model.OrderEntity;
import com.retail.billingSystem.ServiceLayer.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public OrderEntity placeOrder(@RequestBody OrderEntity orderEntity)
    {
        return orderService.placeOrder(orderEntity);
    }

    @GetMapping("/getAllOrders")
    public List<OrderEntity> getallOrders()
    {
        return orderService.getAllOrders();
    }
}

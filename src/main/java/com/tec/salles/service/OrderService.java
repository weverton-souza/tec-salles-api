package com.tec.salles.service;

import com.tec.salles.entity.Order;
import com.tec.salles.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrUpdate(final Order order) {
        if(order.getId() == null) {
            order.setId(UUID.randomUUID().toString());
        }
        return this.orderRepository.save(order);
    }

    public Order findById(final String orderId) {
        return this.orderRepository.findById(orderId).orElseThrow();
    }

    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    public void delete(final String orderId) {
        this.orderRepository.delete(this.findById(orderId));
    }
}

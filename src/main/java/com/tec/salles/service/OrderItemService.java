package com.tec.salles.service;

import com.tec.salles.entity.OrderItem;
import com.tec.salles.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(final OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem saveOrUpdate(final OrderItem orderItem) {
        return this.orderItemRepository.save(orderItem);
    }

    public List<OrderItem> findAll() {
        return this.orderItemRepository.findAll();
    }
}

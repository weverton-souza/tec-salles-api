package com.tec.salles.service;

import com.tec.salles.entity.Order;
import com.tec.salles.entity.OrderItem;
import com.tec.salles.entity.OrderItemPK;
import com.tec.salles.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;

    @Autowired
    public OrderService(final OrderRepository orderRepository, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
    }

    public Order saveOrUpdate(final Order order) {
        if(order.getId() == null) {
            order.setId(UUID.randomUUID().toString());
        }

        final Set<OrderItem> items = order.getItems();
        order.setItems(new HashSet<>());

        Order orderSaved = this.orderRepository.save(order);

        items.forEach(orderItem -> orderItem.setOrder(orderSaved));

        items.forEach(item -> {
            item.setId(new OrderItemPK(item.getProduct(), orderSaved));
            this.orderItemService.saveOrUpdate(item);
        });
        return orderSaved;
    }

    public Order findById(final String orderId) {
        return this.orderRepository.findById(orderId).orElseThrow();
    }

    public Page<Order> findAll(Pageable pageable) {
        return this.orderRepository.findAll(pageable);
    }

    public void delete(final String orderId) {
        this.orderRepository.delete(this.findById(orderId));
    }
}

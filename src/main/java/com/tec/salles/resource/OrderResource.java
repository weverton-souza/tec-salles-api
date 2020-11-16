package com.tec.salles.resource;

import com.tec.salles.entity.Order;
import com.tec.salles.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderResource {

    private final OrderService orderService;

    public OrderResource(final OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody final Order order) {
        return new ResponseEntity<>(this.orderService.saveOrUpdate(order), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Order> update(@RequestBody final Order order) {
        return new ResponseEntity<>(this.orderService.saveOrUpdate(order), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> findById(@PathVariable final String orderId) {
        return new ResponseEntity<>(this.orderService.findById(orderId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        return new ResponseEntity<>(this.orderService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public void delete(@PathVariable final String orderId) {
        this.orderService.delete(orderId);
    }

}

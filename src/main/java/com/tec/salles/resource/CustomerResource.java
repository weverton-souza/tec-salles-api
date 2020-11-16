package com.tec.salles.resource;

import com.tec.salles.entity.Customer;
import com.tec.salles.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerResource {

    private final CustomerService customerService;

    public CustomerResource(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody final Customer customer) {
        return new ResponseEntity<>(this.customerService.saveOrUpdate(customer), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Customer> update(@RequestBody final Customer customer) {
        return new ResponseEntity<>(this.customerService.saveOrUpdate(customer), HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> findById(@PathVariable final String customerId) {
        return new ResponseEntity<>(this.customerService.findById(customerId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        return new ResponseEntity<>(this.customerService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public void delete(@PathVariable final String customerId) {
        this.customerService.delete(customerId);
    }

}

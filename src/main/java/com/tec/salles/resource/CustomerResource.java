package com.tec.salles.resource;

import com.tec.salles.entity.Customer;
import com.tec.salles.entity.Product;
import com.tec.salles.service.CustomerService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customers")
@Api(value = "Customer", tags = ":: CUSTOMER ::", description = "Customer resources")
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

    @GetMapping("/find-by-code/{customerCode}")
    public ResponseEntity<Customer> findByCode(@PathVariable final String customerCode) {
        return new ResponseEntity<>(this.customerService.findByCode(customerCode), HttpStatus.OK);
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

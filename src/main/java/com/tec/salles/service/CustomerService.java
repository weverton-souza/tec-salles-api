package com.tec.salles.service;

import com.tec.salles.entity.Customer;
import com.tec.salles.entity.Product;
import com.tec.salles.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveOrUpdate(final Customer customer) {
        if(customer.getId() == null) {
            customer.setId(UUID.randomUUID().toString());
        }

        return this.customerRepository.save(customer);
    }

    public Customer findById(final String customerId) {
        return this.customerRepository.findById(customerId).orElseThrow();
    }

    public Customer findByCode(final String customerCode) {
        return this.customerRepository.findByCode(customerCode).orElseThrow();
    }

    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    public void delete(final String customerId) {
        this.customerRepository.delete(this.findById(customerId));
    }
}

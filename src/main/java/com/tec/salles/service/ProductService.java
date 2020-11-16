package com.tec.salles.service;

import com.tec.salles.entity.Product;
import com.tec.salles.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveOrUpdate(final Product product) {
        if(product.getId() == null) {
            product.setId(UUID.randomUUID().toString());
        }
        return this.productRepository.save(product);
    }

    public Product findById(final String productId) {
        return this.productRepository.findById(productId).orElseThrow();
    }

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public void delete(final String productId) {
        this.productRepository.delete(this.findById(productId));
    }
}

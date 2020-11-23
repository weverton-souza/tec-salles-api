package com.tec.salles.service;

import com.tec.salles.entity.Product;
import com.tec.salles.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Product findByCode(final String productCode) {
        return this.productRepository.findByCode(productCode).orElseThrow();
    }

    public Page<Product> findAll(final Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    public void delete(final String productId) {
        this.productRepository.deleteById(productId);
    }
}

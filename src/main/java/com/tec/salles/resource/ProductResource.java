package com.tec.salles.resource;

import com.tec.salles.entity.Product;
import com.tec.salles.service.DBService;
import com.tec.salles.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody final Product product) {
        return new ResponseEntity<>(this.productService.saveOrUpdate(product), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody final Product product) {
        return new ResponseEntity<>(this.productService.saveOrUpdate(product), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findById(@PathVariable final String productId) {
        return new ResponseEntity<>(this.productService.findById(productId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() throws ParseException {
        return new ResponseEntity<>(this.productService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public void delete(@PathVariable final String productId) {
        this.productService.delete(productId);
    }

}

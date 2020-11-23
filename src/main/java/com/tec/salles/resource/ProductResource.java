package com.tec.salles.resource;

import com.tec.salles.entity.Product;
import com.tec.salles.service.DBService;
import com.tec.salles.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
@Api(value = "Product", tags = ":: PRODUCT ::", description = "Product resources")
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

    @GetMapping("/find-by-code/{productCode}")
    public ResponseEntity<Product> findByCode(@PathVariable final String productCode) {
        return new ResponseEntity<>(this.productService.findByCode(productCode), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(final Pageable pageable) throws ParseException {
        return new ResponseEntity<>(this.productService.findAll(pageable), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public void delete(@PathVariable final String productId) {
        this.productService.delete(productId);
    }

}

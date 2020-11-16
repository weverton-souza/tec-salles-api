package com.tec.salles.resource;

import com.tec.salles.entity.Category;
import com.tec.salles.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategoryResource {

    private final CategoryService categoryService;

    public CategoryResource(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody final Category category) {
        return new ResponseEntity<>(this.categoryService.saveOrUpdate(category), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Category> update(@RequestBody final Category category) {
        return new ResponseEntity<>(this.categoryService.saveOrUpdate(category), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> findById(@PathVariable final String categoryId) {
        return new ResponseEntity<>(this.categoryService.findById(categoryId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return new ResponseEntity<>(this.categoryService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable final String categoryId) {
        this.categoryService.delete(categoryId);
    }

}

package com.tec.salles.resource;

import com.tec.salles.entity.Category;
import com.tec.salles.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/categories")
@Api(value = "Category", tags = ":: CATEGORY ::", description = "Category resources")
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
    public ResponseEntity<Page<Category>> findAll(final Pageable pageable) {
        return new ResponseEntity<>(this.categoryService.findAll(pageable), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable final String categoryId) {
        this.categoryService.delete(categoryId);
    }

}

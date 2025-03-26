package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.rest;

import com.acsolutions.arnulfocastrillon.acsolutions.application.CategoryService;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.Category;
import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.adapter.CategoryCrudRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/categories")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryCrudRepositoryImpl categoryCrudRepository;

    public CategoryController(CategoryService categoryService, CategoryCrudRepositoryImpl categoryCrudRepository) {
        this.categoryService = categoryService;
        this.categoryCrudRepository = categoryCrudRepository;
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.save(category),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Category>> findAll(){
        return ResponseEntity.ok(categoryService.findByActivoTrue());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Integer id){
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deactivate(@PathVariable Integer id){
        categoryCrudRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}

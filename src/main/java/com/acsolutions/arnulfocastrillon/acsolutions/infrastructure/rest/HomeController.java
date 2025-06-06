package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.rest;

import com.acsolutions.arnulfocastrillon.acsolutions.application.CategoryService;
import com.acsolutions.arnulfocastrillon.acsolutions.application.ProductService;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.Category;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/home")
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public HomeController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll (){
        return ResponseEntity.ok(productService.findByActivoTrue());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById (@PathVariable Integer id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/categories")
    public ResponseEntity<Iterable<Category>> findAllCategories(){
        return ResponseEntity.ok(categoryService.findByActivoTrue());
    }
}

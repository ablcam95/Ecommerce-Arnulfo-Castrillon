package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.rest;

import com.acsolutions.arnulfocastrillon.acsolutions.application.ProductService;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/products")
@Slf4j
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product){
        log.info("El nombre del producto es: {} ", product.getName());
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll (){
        return ResponseEntity.ok(productService.findByActivoTrue());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById (@PathVariable Integer id){
        return ResponseEntity.ok(productService.findById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteById (@PathVariable Integer id){
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}

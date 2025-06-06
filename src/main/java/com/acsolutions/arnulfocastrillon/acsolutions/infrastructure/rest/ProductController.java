package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.rest;

import com.acsolutions.arnulfocastrillon.acsolutions.application.ProductService;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/admin/products")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> save(
                                        @RequestParam(value = "id", required = false) Integer id,
                                        @RequestParam("code") String code,
                                        @RequestParam("name") String name,
                                        @RequestParam("tipo") String tipo,
                                        @RequestParam("capacidad") String capacidad,
                                        @RequestParam("description") String description,
                                        @RequestParam("price") BigDecimal price,
                                        @RequestParam("urlImage") String urlImage,
                                        @RequestParam("userId") Integer userId,
                                        @RequestParam("categoryId") Integer categoryId,
                                        @RequestParam(value = "image",required = false) MultipartFile multipartFile
                                        ) throws IOException {

        Product product = new Product();
        product.setId(id);
        product.setCode(code);
        product.setName(name);
        product.setCapacidad(capacidad);
        product.setTipo(tipo);
        product.setDescription(description);
        product.setPrice(price);
        product.setUrlImage(urlImage);
        product.setUserId(userId);
        product.setCategoryId(categoryId);

        log.info("El nombre del producto es: {} ", product.getName());
        return new ResponseEntity<>(productService.save(product,multipartFile), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Integer id,
            @RequestParam("code") String code,
            @RequestParam("name") String name,
            @RequestParam("tipo") String tipo,
            @RequestParam("capacidad") String capacidad,
            @RequestParam("description") String description,
            @RequestParam("price") BigDecimal price,
            @RequestParam("urlImage") String urlImage,
            @RequestParam("userId") Integer userId,
            @RequestParam("categoryId") Integer categoryId,
            @RequestParam(value = "image", required = false) MultipartFile multipartFile
    ) throws IOException {

        Product product = new Product();
        product.setId(id); // Aseguramos que el ID venga de la URL y no del body
        product.setCode(code);
        product.setName(name);
        product.setCapacidad(capacidad);
        product.setTipo(tipo);
        product.setDescription(description);
        product.setPrice(price);
        product.setUrlImage(urlImage);
        product.setUserId(userId);
        product.setCategoryId(categoryId);

        log.info("Actualizando producto con ID: {}", id);
        Product updatedProduct = productService.update(product, multipartFile);

        return ResponseEntity.ok(updatedProduct);
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

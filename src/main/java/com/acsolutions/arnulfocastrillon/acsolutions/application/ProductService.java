package com.acsolutions.arnulfocastrillon.acsolutions.application;

import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.Product;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.port.IProductRepository;


public class ProductService {
    private final IProductRepository iProductRepository;
    public ProductService(IProductRepository iProductRepository) {

        this.iProductRepository = iProductRepository;
    }
    public Iterable<Product> findByActivoTrue() {
        return iProductRepository.findByActivoTrue();
    }
    public void deleteById(Integer id) {
        this.iProductRepository.deleteById(id);
    }
    public Product findById(Integer id) {
        return this.iProductRepository.findById(id);
    }
    public Product save(Product product) {
        return this.iProductRepository.save(product);
    }
}

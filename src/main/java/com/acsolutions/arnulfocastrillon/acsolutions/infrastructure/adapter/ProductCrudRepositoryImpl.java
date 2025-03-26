package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.adapter;

import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.Product;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.port.IProductRepository;
import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.entity.ProductEntity;
import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
public class ProductCrudRepositoryImpl implements IProductRepository {

    private final IProductCrudRepository iProductCrudRepository;
    private final ProductMapper productMapper;

    public ProductCrudRepositoryImpl(IProductCrudRepository iProductCrudRepository, ProductMapper productMapper) {
        this.iProductCrudRepository = iProductCrudRepository;
        this.productMapper = productMapper;
    }


    @Override
    public Product findById(Integer id) {
        return productMapper.toProduct(iProductCrudRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Producto con el id: " + id + "no se encuentra")
        ));
    }

    @Override
    public Iterable<Product> findByActivoTrue() {
        return productMapper.toProductList(iProductCrudRepository.findAll());
    }

    @Override
    public Product save(Product product) {
        return productMapper.toProduct(iProductCrudRepository.save(productMapper.toProductEntity(product)));
    }

    @Override
    public void deleteById(Integer id) {
        iProductCrudRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Producto con el id: " + id + "no se encuentra")
        );

        iProductCrudRepository.deleteById(id);
    }
}

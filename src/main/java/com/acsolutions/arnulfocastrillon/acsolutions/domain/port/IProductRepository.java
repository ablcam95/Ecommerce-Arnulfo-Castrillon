package com.acsolutions.arnulfocastrillon.acsolutions.domain.port;

import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.Product;



public interface IProductRepository {
    //buscar un producto por id
    Product findById(Integer id);
    // listar productos
    Iterable<Product> findByActivoTrue();
    // guardar producto
    Product save(Product product);
    // Desactivar producto en lugar de eliminarlo
    void deleteById(Integer id);


}

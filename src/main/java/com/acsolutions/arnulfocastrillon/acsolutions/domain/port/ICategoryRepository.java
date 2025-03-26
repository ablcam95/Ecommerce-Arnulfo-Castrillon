package com.acsolutions.arnulfocastrillon.acsolutions.domain.port;

import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.Category;

public interface ICategoryRepository {
    // listar categoria
    Iterable<Category> findByActivoTrue();
    // guardar category
    Category save(Category category);

    //String deactivate(Integer id);

    void deleteById(Integer id);

    // buscar categoria por Id
    Category findById(Integer id);
}

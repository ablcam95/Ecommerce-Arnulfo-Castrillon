package com.acsolutions.arnulfocastrillon.acsolutions.application;

import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.Category;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.port.ICategoryRepository;

public class CategoryService {
    private final ICategoryRepository iCategoryRepository;

    public CategoryService(ICategoryRepository iCategoryRepository) {
        this.iCategoryRepository = iCategoryRepository;
    }

    public Category save(Category category) {
        return iCategoryRepository.save(category);
    }

    public Iterable<Category> findByActivoTrue() {
        return iCategoryRepository.findByActivoTrue();
    }

    public Category findById(Integer id) {
        return iCategoryRepository.findById(id);
    }

    public void deleteById(Integer id){
        iCategoryRepository.deleteById(id);
    }

}

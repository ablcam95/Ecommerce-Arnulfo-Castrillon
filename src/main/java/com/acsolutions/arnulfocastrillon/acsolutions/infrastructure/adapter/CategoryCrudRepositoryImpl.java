package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.adapter;

import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.Category;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.port.ICategoryRepository;
import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.mapper.CategoryMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryCrudRepositoryImpl implements ICategoryRepository {
    private final ICategoryCrudRepository iCategoryCrudRepository;
    private final CategoryMapper categoryMapper;


    public CategoryCrudRepositoryImpl(ICategoryCrudRepository iCategoryCrudRepository, CategoryMapper categoryMapper) {
        this.iCategoryCrudRepository = iCategoryCrudRepository;
        this.categoryMapper = categoryMapper;

    }

    @Override
    public Iterable<Category> findByActivoTrue() {
        return categoryMapper.toCategories(iCategoryCrudRepository.findAll());
    }

    @Override
    public Category save(Category category) {
        return categoryMapper.toCategory(iCategoryCrudRepository.save(categoryMapper.toCategoryEntity(category)));
    }

    @Override
    public void deleteById(Integer id) {
        iCategoryCrudRepository.findById(id).orElseThrow(
                () -> new RuntimeException("La categoria con Id: "+id+" no existe")
        );

       iCategoryCrudRepository.deleteById(id);

    }

    //@Override
    //public String deactivate(Integer id) {
    //    categoryresp  =  iCategoryCrudRepository.findById(id).orElse(null);
    //    assert categoryresp != null;
    //    categoryresp.setActivo(false);
    //    categoryMapper.toCategory(iCategoryCrudRepository.save(categoryresp));

    //    return ("guardado exitoso");
    //}

    @Override
    public Category findById(Integer id) {
        return categoryMapper.toCategory(iCategoryCrudRepository.findById(id).orElseThrow(
                () -> new RuntimeException("La categoria con Id: "+id+" no existe")
        ));
    }
}

package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.adapter;

import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryCrudRepository extends CrudRepository <CategoryEntity,Integer> {

}

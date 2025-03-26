package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.adapter;


import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.entity.ProductEntity;

import org.springframework.data.repository.CrudRepository;

public interface IProductCrudRepository extends CrudRepository<ProductEntity,Integer> {



}

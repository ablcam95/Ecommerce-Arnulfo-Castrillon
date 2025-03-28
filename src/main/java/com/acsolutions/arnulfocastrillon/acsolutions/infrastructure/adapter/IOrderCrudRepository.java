package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.adapter;

import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.OrderState;
import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.entity.OrderEntity;
import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IOrderCrudRepository extends CrudRepository<OrderEntity,Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE OrderEntity o SET o.orderState = :state WHERE o.id = :id")
    void updateStateById (Integer id, OrderState state);

    List<OrderEntity> findByUserEntity(UserEntity userEntity);

}

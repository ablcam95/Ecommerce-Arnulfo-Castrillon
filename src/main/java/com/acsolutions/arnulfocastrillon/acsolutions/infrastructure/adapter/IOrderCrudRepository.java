package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.adapter;

import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.OrderState;
import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.entity.OrderEntity;
import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.entity.UserEntity;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IOrderCrudRepository extends CrudRepository<OrderEntity,Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE OrderEntity o SET o.state = :state WHERE o.id = :id")
    void updateStateById (Integer id, OrderState state);

    Iterable<OrderEntity> findByUserEntity(UserEntity userEntity);

}

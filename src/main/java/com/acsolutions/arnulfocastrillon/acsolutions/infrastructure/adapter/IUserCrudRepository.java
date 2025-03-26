package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.adapter;

import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.entity.OrderEntity;
import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserCrudRepository extends CrudRepository<UserEntity,Integer> {

    @Query(value = "SELECT * FROM users WHERE activo is true", nativeQuery = true)
    List<UserEntity> findByActivo();



    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.activo = false WHERE u.id = :id")
    void updateActivo(@Param("id") Integer id);

    boolean existsById(Integer id);

    @Modifying
    @Query("UPDATE UserEntity u SET u.activo = true WHERE u.id = :id")
    void reactivateUser(@Param("id") Integer id);







}

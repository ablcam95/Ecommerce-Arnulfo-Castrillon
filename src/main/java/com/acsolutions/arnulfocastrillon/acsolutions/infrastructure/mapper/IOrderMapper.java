package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.mapper;

import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.Order;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.OrderProduct;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.OrderState;
import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.entity.OrderEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring",uses = IOrderProductMapper.class)
public interface IOrderMapper {
    @Mappings(
            {       @Mapping(source = "id" , target = "id"),
                    @Mapping(source = "dateCreated" , target = "dateCreated"),
                    @Mapping(source="orderProducts",target = "orderProducts"),
                    @Mapping(source = "orderState",target = "orderState"),
                    @Mapping(source= "userEntity.id",target = "userId")
            }
    )
    Order toOrder(OrderEntity orderEntity);
    Iterable<Order> toOrderList(Iterable<OrderEntity> orderEntities);

    @InheritInverseConfiguration
    OrderEntity toOrderEntity(Order order);
}

package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.mapper;

import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.OrderProduct;
import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.entity.OrderProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IOrderProductMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "productId", target = "productId"),

    })
    OrderProduct toOrderProduct(OrderProductEntity orderProductEntity);
    List<OrderProduct> toOrderProductList(List<OrderProductEntity> orderProductEntities);

    @InheritInverseConfiguration

    OrderProductEntity toOrderProductEntity(OrderProduct orderProduct);

}

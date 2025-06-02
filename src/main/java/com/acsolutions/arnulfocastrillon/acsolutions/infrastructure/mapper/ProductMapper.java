package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.mapper;

import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.Product;
import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.entity.ProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mappings(
            {
                    @Mapping(source = "id" , target = "id"),
                    @Mapping(source = "name" , target = "name"),
                    @Mapping(source = "code" , target = "code"),
                    @Mapping(source = "capacidad" , target = "capacidad"),
                    @Mapping(source = "tipo" , target = "tipo"),
                    @Mapping(source = "jacuzzi" , target = "jacuzzi"),
                    @Mapping(source = "description" , target = "description"),
                    @Mapping(source = "urlImage" , target = "urlImage"),
                    @Mapping(source = "price" , target = "price"),
                    @Mapping(source = "activo" , target = "activo"),
                    @Mapping(source = "dateCreated" , target = "dateCreated"),
                    @Mapping(source = "dateUpdated" , target = "dateUpdated"),
                    @Mapping(source = "userEntity.id" , target = "userId"),
                    @Mapping(source = "categoryEntity.id" , target = "categoryId"),


            }
    )


    Product toProduct(ProductEntity productEntity);
    Iterable<Product> toProductList(Iterable<ProductEntity> productEntities);

    @InheritInverseConfiguration
    ProductEntity toProductEntity (Product product);
    Iterable<ProductEntity> toProductEntityList (Iterable<Product> products);

}

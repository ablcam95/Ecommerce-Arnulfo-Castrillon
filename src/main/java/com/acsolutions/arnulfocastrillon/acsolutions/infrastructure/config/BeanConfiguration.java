package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.config;

import com.acsolutions.arnulfocastrillon.acsolutions.application.CategoryService;
import com.acsolutions.arnulfocastrillon.acsolutions.application.ProductService;
import com.acsolutions.arnulfocastrillon.acsolutions.application.UserService;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.port.ICategoryRepository;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.port.IProductRepository;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.port.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class BeanConfiguration {

    @Bean
    public UserService userService(IUserRepository iUserRepository){
        return new UserService(iUserRepository);
    }
    @Bean
    public CategoryService categoryService(ICategoryRepository iCategoryRepository){
        return new CategoryService(iCategoryRepository);
    }
    @Bean
    public ProductService productService(IProductRepository iProductRepository){
        return new ProductService (iProductRepository);
    }
}

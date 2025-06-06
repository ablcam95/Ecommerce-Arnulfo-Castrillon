package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.config;

import com.acsolutions.arnulfocastrillon.acsolutions.application.*;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.port.ICategoryRepository;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.port.IOrderRepository;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.port.IProductRepository;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.port.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    // Services
    @Bean
    public UserService userService(IUserRepository iUserRepository) {
        return new UserService(iUserRepository);
    }

    @Bean
    public CategoryService categoryService(ICategoryRepository iCategoryRepository) {
        return new CategoryService(iCategoryRepository);
    }

    @Bean
    public ProductService productService(IProductRepository iProductRepository, UploadFile uploadFile) {
        return new ProductService(iProductRepository, uploadFile);
    }

    @Bean
    public OrderService orderService(IOrderRepository iOrderRepository) {
        return new OrderService(iOrderRepository);
    }

    // Utilities
    @Bean
    public UploadFile uploadFile() {
        return new UploadFile();
    }

    @Bean
    public RegistrationService registrationService(IUserRepository iUserRepository){
        return new RegistrationService(iUserRepository);
    }
}

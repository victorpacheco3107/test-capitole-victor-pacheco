package com.capitole.victorpacheco.launcher.configuration;

import com.capitole.victorpacheco.domain.service.DomainProductService;
import com.capitole.victorpacheco.domain.service.ProductService;
import com.capitole.victorpacheco.infrastructure.repository.ProductHttpRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public ProductService productService(ProductHttpRepository productHttpRepository){
        return new DomainProductService(productHttpRepository);
    }

}

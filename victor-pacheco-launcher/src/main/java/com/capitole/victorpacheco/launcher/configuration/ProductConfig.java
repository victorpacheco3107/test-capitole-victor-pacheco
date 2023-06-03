package com.capitole.victorpacheco.launcher.configuration;

import com.capitole.victorpacheco.domain.service.DomainProductService;
import com.capitole.victorpacheco.domain.service.ProductService;
import com.capitole.victorpacheco.infrastructure.httpclient.ProductHttpClient;
import com.capitole.victorpacheco.infrastructure.mapper.ProductMapper;
import com.capitole.victorpacheco.infrastructure.repository.ProductHttpRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public ProductService productService(ProductHttpClient productHttpClient, ProductMapper productMapper){
        return new DomainProductService(new ProductHttpRepository(productHttpClient, productMapper));
    }

}

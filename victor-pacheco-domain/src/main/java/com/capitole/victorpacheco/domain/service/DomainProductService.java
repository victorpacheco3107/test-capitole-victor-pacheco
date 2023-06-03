package com.capitole.victorpacheco.domain.service;

import com.capitole.victorpacheco.domain.Product;
import com.capitole.victorpacheco.domain.repository.ProductRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DomainProductService implements ProductService{

    private final ProductRepository productRepository;

    public DomainProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findSimilarProductsById(Integer productId) {
        return productRepository.findSimilarProductsIdById(productId)
            .orElse(Collections.emptyList())
            .parallelStream()
            .map(similarProductId -> productRepository.findProductById(similarProductId))
            .filter(productOptional -> productOptional.isPresent())
            .map(productOptional -> productOptional.get())
            .collect(Collectors.toList());
    }
}

package com.capitole.victorpacheco.domain.service;

import com.capitole.victorpacheco.domain.Product;
import com.capitole.victorpacheco.domain.repository.ProductRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the interface {@link  com.capitole.victorpacheco.domain.service.ProductService  ProductService}
 * @author <a href="mailto:victorpacheco3107@gmail.com">Victor Uriel Pacheco Castro</a>
 * @since 1.0
 */
public class DomainProductService implements ProductService{

    /**
     * Repository for query operations.
     */
    private final ProductRepository productRepository;

    public DomainProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * @see com.capitole.victorpacheco.domain.service.ProductService#findSimilarProductsById(Integer)
     */
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

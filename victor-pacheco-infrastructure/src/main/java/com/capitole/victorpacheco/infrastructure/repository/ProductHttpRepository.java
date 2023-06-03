package com.capitole.victorpacheco.infrastructure.repository;

import com.capitole.victorpacheco.domain.Product;
import com.capitole.victorpacheco.domain.repository.ProductRepository;
import com.capitole.victorpacheco.infrastructure.httpclient.ProductHttpClient;
import com.capitole.victorpacheco.infrastructure.httpclient.SimilarIdsResponse;
import com.capitole.victorpacheco.infrastructure.mapper.ProductMapper;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class ProductHttpRepository implements ProductRepository {

    private final ProductHttpClient productHttpClient;
    private final ProductMapper productMapper;

    public ProductHttpRepository(ProductHttpClient productHttpClient, ProductMapper productMapper) {
        this.productHttpClient = productHttpClient;
        this.productMapper = productMapper;
    }

    @Override
    public Optional<Product> findProductById(Integer productId) {
        try {
            log.info("Finding product with id {}", productId);
            Product product = productMapper.productDetailResponseToEntity(productHttpClient.findProductById(productId));
            log.info("Product with id {} found", productId);
            return Optional.of(product);
        } catch (FeignException ex){
            if(ex.status() == 404){
                log.warn("Product with id {} not found", productId);
            } else {
                log.warn("Error finding product with id {}", productId, ex);
            }
        } catch (Exception ex){
            log.warn("Error finding product with id {}", productId, ex);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Integer>> findSimilarProductsIdById(Integer productId) {
        try {
            log.info("Finding similar ids of product with id {}", productId);
            SimilarIdsResponse similarProductIds = productHttpClient.findSimilarProductIdsById(productId);
            log.info("Similar ids of product with id {} ok", productId);
            return Optional.of(similarProductIds);
        } catch (FeignException ex){
            if(ex.status() == 404){
                log.warn("Similar ids of product with id {} not found", productId);
            } else {
                log.warn("Error finding similar ids of product with id {}", productId, ex);
            }
        } catch (Exception ex){
            log.warn("Error finding similar ids of product with id {}", productId, ex);
        }
        return Optional.empty();
    }
}

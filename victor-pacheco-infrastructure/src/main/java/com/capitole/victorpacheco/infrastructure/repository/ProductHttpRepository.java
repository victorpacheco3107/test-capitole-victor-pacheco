package com.capitole.victorpacheco.infrastructure.repository;

import com.capitole.victorpacheco.domain.Product;
import com.capitole.victorpacheco.domain.repository.ProductRepository;
import com.capitole.victorpacheco.infrastructure.mapper.ProductClientMapper;
import com.capitole.victorpacheco.infrastructure.productclient.ProductClient;
import com.capitole.victorpacheco.infrastructure.productclient.SimilarIdsResponse;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductHttpRepository implements ProductRepository {

    private final ProductClient productClient;
    private final ProductClientMapper productClientMapper;

    public ProductHttpRepository(ProductClient productClient, ProductClientMapper productClientMapper) {
        this.productClient = productClient;
        this.productClientMapper = productClientMapper;
    }

    @Override
    @Cacheable("productDetail")
    public Optional<Product> findProductById(Integer productId) {
        try {
            log.info("Finding product with id {}", productId);
            Product product = productClientMapper.productDetailResponseToEntity(productClient.findProductById(productId));
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
    @Cacheable("similarIds")
    public Optional<List<Integer>> findSimilarProductsIdById(Integer productId) {
        try {
            log.info("Finding similar ids of product with id {}", productId);
            SimilarIdsResponse similarProductIds = productClient.findSimilarProductIdsById(productId);
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

package com.capitole.victorpacheco.domain.service;

import com.capitole.victorpacheco.domain.Product;

import java.util.List;

/**
 * Class with business logic for {@link  com.capitole.victorpacheco.domain.Product  Product} entity.
 * @author <a href="mailto:victorpacheco3107@gmail.com">Victor Uriel Pacheco Castro</a>
 * @since 1.0
 */
public interface ProductService {

    /**
     * Find the details of similar products of a {@link  com.capitole.victorpacheco.domain.Product  Product}.
     * @param id {@link  com.capitole.victorpacheco.domain.Product  Product} id to which you want to find its similar ones.
     * @return Details of similar products.
     */
    List<Product> findSimilarProductsById(Integer id);

}

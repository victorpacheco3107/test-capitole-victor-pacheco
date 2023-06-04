package com.capitole.victorpacheco.domain.repository;

import com.capitole.victorpacheco.domain.Product;

import java.util.List;
import java.util.Optional;

/**
 * Repository for query operations on the {@link  com.capitole.victorpacheco.domain.Product  Product} entity.
 * @author <a href="mailto:victorpacheco3107@gmail.com">Victor Uriel Pacheco Castro</a>
 * @since 1.0
 */
public interface ProductRepository {

    /**
     * Find a product given the {@link  com.capitole.victorpacheco.domain.Product  Product}'s id.
     * @param id {@link  com.capitole.victorpacheco.domain.Product  Product} identification to find.
     * @return Optional object with {@link  com.capitole.victorpacheco.domain.Product  Product} information if it exists,
     * otherwise an optional empty is returned.
     */
    Optional<Product> findProductById(Integer id);

    /**
     * Find the ids of the similar {@link  com.capitole.victorpacheco.domain.Product  Product}s of a {@link  com.capitole.victorpacheco.domain.Product  Product}.
     * @param id {@link  com.capitole.victorpacheco.domain.Product  Product} id to which you want to find its similar ones.
     * @return Optional object with similar ids.
     * If any error occurs during the query, an optional empty is returned.
     */
    Optional<List<Integer>> findSimilarProductsIdById(Integer id);

}

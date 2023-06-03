package com.capitole.victorpacheco.domain.repository;

import com.capitole.victorpacheco.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findProductById(Integer id);

    Optional<List<Integer>> findSimilarProductsIdById(Integer id);

}

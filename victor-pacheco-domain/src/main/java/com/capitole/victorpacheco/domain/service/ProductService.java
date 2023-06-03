package com.capitole.victorpacheco.domain.service;

import com.capitole.victorpacheco.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> findSimilarProductsById(Integer id);

}

package com.capitole.victorpacheco.application.controller;

import com.capitole.victorpacheco.application.mapper.ProductResponseMapper;
import com.capitole.victorpacheco.application.response.ProductResponse;
import com.capitole.victorpacheco.domain.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductResponseMapper productResponseMapper;
    private final ProductService productService;

    public ProductController(ProductResponseMapper productResponseMapper, ProductService productService) {
        this.productResponseMapper = productResponseMapper;
        this.productService = productService;
    }

    @GetMapping("/{productId}/similar")
    public ResponseEntity<List<ProductResponse>> findSimilarProducts(@PathVariable Integer productId){
        List<ProductResponse> similarProducts = productResponseMapper.productsToResponse(
            productService.findSimilarProductsById(productId)
        );
        return similarProducts.isEmpty() ?
            ResponseEntity.notFound().build() :
            ResponseEntity.ok(similarProducts);
    }

}

package com.capitole.victorpacheco.infrastructure.httpclient;

import feign.Param;
import feign.RequestLine;

public interface ProductHttpClient {
    @RequestLine("GET /product/{productId}")
    DetailProductResponse findProductById(@Param("productId") Integer productId);

    @RequestLine("GET /product/{productId}/similarids")
    SimilarIdsResponse findSimilarProductIdsById(@Param("productId") Integer productId);

}

package com.capitole.victorpacheco.application.mapper;

import com.capitole.victorpacheco.application.response.ProductResponse;
import com.capitole.victorpacheco.domain.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductResponseMapper {

    List<ProductResponse> productsToResponse(List<Product> products);

}

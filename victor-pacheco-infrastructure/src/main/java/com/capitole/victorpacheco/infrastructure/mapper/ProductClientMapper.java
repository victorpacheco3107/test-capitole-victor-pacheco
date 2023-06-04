package com.capitole.victorpacheco.infrastructure.mapper;

import com.capitole.victorpacheco.domain.Product;
import com.capitole.victorpacheco.infrastructure.productclient.DetailProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductClientMapper {

    Product productDetailResponseToEntity(DetailProductResponse detailProductResponse);

}

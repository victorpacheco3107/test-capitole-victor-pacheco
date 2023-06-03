package com.capitole.victorpacheco.infrastructure.mapper;

import com.capitole.victorpacheco.domain.Product;
import com.capitole.victorpacheco.infrastructure.httpclient.DetailProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product productDetailResponseToEntity(DetailProductResponse detailProductResponse);

}

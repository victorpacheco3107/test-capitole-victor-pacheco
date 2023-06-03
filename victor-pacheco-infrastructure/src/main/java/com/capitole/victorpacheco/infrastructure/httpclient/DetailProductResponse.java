package com.capitole.victorpacheco.infrastructure.httpclient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetailProductResponse {
    private Integer id;
    private String name;
    private Double price;
    private Boolean availability;
}

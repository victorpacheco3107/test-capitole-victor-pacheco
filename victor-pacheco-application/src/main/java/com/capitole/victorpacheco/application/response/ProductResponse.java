package com.capitole.victorpacheco.application.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponse {
    private Integer id;
    private String name;
    private Double price;
    private Boolean availability;
}

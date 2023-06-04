package com.capitole.victorpacheco.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity that represents a product.
 * @author <a href="mailto:victorpacheco3107@gmail.com">Victor Uriel Pacheco Castro</a>
 * @since 1.0
 */
@Data
@NoArgsConstructor
public class Product {
    /**
     * Product identification.
     */
    private Integer id;
    /**
     * Product name.
     */
    private String name;
    /**
     * Product price.
     */
    private Double price;
    /**
     * Product availability.
     */
    private Boolean availability;
}

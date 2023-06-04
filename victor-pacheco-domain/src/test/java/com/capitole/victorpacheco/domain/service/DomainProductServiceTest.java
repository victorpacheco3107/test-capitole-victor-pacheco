package com.capitole.victorpacheco.domain.service;

import com.capitole.victorpacheco.domain.Product;
import com.capitole.victorpacheco.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class DomainProductServiceTest {

    private DomainProductService subject;

    private ProductRepository productRepository;


    @BeforeEach
    public void setUp() {
        this.productRepository = Mockito.mock(ProductRepository.class);
        this.subject = new DomainProductService(this.productRepository);
    }

    @Test
    public void Should_ReturnEmptyList_When_RepositoryReturnEmptyOptional(){

        Integer productId = 1;

        when(productRepository.findSimilarProductsIdById(productId)).thenReturn(Optional.empty());

        List<Product> similarProducts = subject.findSimilarProductsById(productId);

        assertNotNull(similarProducts);
        assertEquals(similarProducts.size(), 0);
    }

    @Test
    public void Should_ReturnEmptyList_When_RepositoryReturnOptionalWithEmptyList(){

        Integer productId = 1;

        when(productRepository.findSimilarProductsIdById(productId)).thenReturn(Optional.of(Collections.emptyList()));

        List<Product> similarProducts = subject.findSimilarProductsById(productId);

        assertNotNull(similarProducts);
        assertEquals(similarProducts.size(), 0);
    }

    @Test
    public void Should_ReturnListOneObject_When_RepositoryReturnOneSimilar_And_SimilarDetailsExist(){

        Integer productId = 1;

        Integer productSimilarId = 2;

        Product product = getRandomProduct(productSimilarId);

        when(productRepository.findSimilarProductsIdById(productId)).thenReturn(Optional.of(Collections.singletonList(productSimilarId)));
        when(productRepository.findProductById(productSimilarId)).thenReturn(Optional.of(product));

        List<Product> similarProducts = subject.findSimilarProductsById(productId);

        assertNotNull(similarProducts);
        assertEquals(similarProducts.size(), 1);

        Product productExpected = similarProducts.get(0);

        assertEquals(productExpected.getId(), product.getId());
        assertEquals(productExpected.getAvailability(), product.getAvailability());
        assertEquals(productExpected.getPrice(), product.getPrice());
        assertEquals(productExpected.getName(), product.getName());
    }

    @Test
    public void Should_ReturnEmptyList_When_RepositoryReturnOneSimilar_And_SimilarDetailsNotExist(){

        Integer productId = 1;

        Integer productSimilarId = 2;

        when(productRepository.findSimilarProductsIdById(productId)).thenReturn(Optional.of(Collections.singletonList(productSimilarId)));
        when(productRepository.findProductById(productSimilarId)).thenReturn(Optional.empty());

        List<Product> similarProducts = subject.findSimilarProductsById(productId);

        assertNotNull(similarProducts);
        assertEquals(similarProducts.size(), 0);

    }

    private Product getRandomProduct(Integer productId){
        Product product = new Product();
        product.setId(productId);

        Random random = new Random();

        product.setPrice(random.nextDouble());
        product.setAvailability(random.nextBoolean());

        byte[] array = new byte[7];
        random.nextBytes(array);
        product.setName(new String(array, Charset.forName("UTF-8")));

        return product;

    }

}

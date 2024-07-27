package dev.naman.productservicettsmorningdeb24.controllers;

import dev.naman.productservicettsmorningdeb24.exceptions.ProductNotFoundException;
import dev.naman.productservicettsmorningdeb24.models.Product;
import dev.naman.productservicettsmorningdeb24.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    @Qualifier("fakeStoreProductService")
    private ProductService productService;

    @Test
    void createProduct() {
    }

//    @Test
//    void getProductDetailsPositive() throws ProductNotFoundException {
//        Product product = new Product();
//        product.setTitle("MacBook pro");
//        product.setPrice(200000);
//        product.setDescription("Best Macbook ever");
//
//        when(productService.getSingleProduct(anyLong()))
//                .thenReturn(product);
//
//        Product response = productController.getProductDetails(10L, "klsjdklgf");
//
//        assertEquals(product,
//                response,
//                "getSingleProduct API isn't returning the correct product object.");
//    }

    @Test
    void getProductDetailsNegative() throws ProductNotFoundException {
        when(productService.getSingleProduct(1000L))
                .thenThrow(ProductNotFoundException.class);

//        assertThrows(
//                ProductNotFoundException.class,
//                () -> productController.getProductDetails(1000L)
//        );
    }

    @Test
    void getAllProductsPositive() throws ProductNotFoundException {
        //Call the mocked ProductService and get a list of Products.

        Product p1 = new Product();
        p1.setTitle("iPhone 15");
        p1.setImageUrl("img");
        p1.setDescription("2023 iPhone");

        Product p2 = new Product();
        p2.setTitle("iPhone 15 pro");
        p2.setImageUrl("img");
        p2.setDescription("2023 iPhone pro");

        Product p3 = new Product();
        p3.setTitle("iPhone 15 pro max");
        p3.setImageUrl("img");
        p3.setDescription("2023 iPhone pro max");

        List<Product> products = new ArrayList<>();
        products.add(p1);
        products.add(p2);
        products.add(p3);

        when(
                productService.getProducts()
        ).thenReturn(
                products
        );

        //Actual response
        ResponseEntity<List<Product>> responseEntity = productController.getAllProducts();
        List<Product> response = responseEntity.getBody();

        //Ideally products should be exactly same as response list.
        assertEquals(products.size(), response.size());
        assertEquals(products, response);
    }

    @Test
    void updateProduct() {
    }
}

//MOCKING -> Test in Isolation.
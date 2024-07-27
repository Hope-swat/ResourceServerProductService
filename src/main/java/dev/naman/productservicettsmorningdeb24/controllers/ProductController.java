package dev.naman.productservicettsmorningdeb24.controllers;

import dev.naman.productservicettsmorningdeb24.commons.AuthenticationCommons;
import dev.naman.productservicettsmorningdeb24.dtos.CreateProductRequestDto;
import dev.naman.productservicettsmorningdeb24.dtos.UserDto;
import dev.naman.productservicettsmorningdeb24.exceptions.InvalidTokenException;
import dev.naman.productservicettsmorningdeb24.exceptions.ProductNotFoundException;
import dev.naman.productservicettsmorningdeb24.models.Product;
import dev.naman.productservicettsmorningdeb24.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController {

//    private Map<Integer, Integer> mp = new TreeMap<>();
//    List<Integer> li = new ArrayList<>();

    private ProductService productService;
    private RestTemplate restTemplate;
    private AuthenticationCommons authenticationCommons;

//    private ProductService productService2 = new FakeStoreProductService();


    public ProductController(@Qualifier("selfProductService") ProductService productService,
                             RestTemplate restTemplate,
                             AuthenticationCommons authenticationCommons
    ) {
        this.productService = productService;
        this.restTemplate = restTemplate;
        this.authenticationCommons = authenticationCommons;
    }

// private ProductService productService;

    // POST /products
    // RequestBody
    // {
    //     title:
    //     description:
    //     price:
    //     category: "mobile"
    // }
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto request) {
        return productService.createProduct(
                request.getTitle(),
                request.getDescription(),
                request.getCategory(),
                request.getPrice(),
                request.getImage()
        );
    }

    // GET /products/1
    // GET /products/201

    @GetMapping("/products/{id}/{token}")
    public Product getProductDetails(@PathVariable("id") Long productId, @PathVariable("token") String token) throws ProductNotFoundException, InvalidTokenException {

//        UserDto userDto = authenticationCommons.validateToken(token);

//        if (userDto == null) {
//            //Token is invalid.
//            throw new InvalidTokenException("Invalid token passed, please login first to get the Product details");
//        }

        UserDto userDto =
                restTemplate.getForObject("http://userservice/users/1", UserDto.class);

        //Token is valid, make a call to Product Service to fetch the product.
        return productService.getSingleProduct(productId);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() throws ProductNotFoundException {

        List<Product> products = productService.getProducts();

//        throw new ProductNotFoundException("Bla bla bla");

//        for (Product product : products) {
//            product.setTitle("Hello " + product.getTitle());
//        }

//        List<Product> products1 = new ArrayList<>();
//        products1.add(new Product());
//        products1.add(new Product());
//        products1.add(new Product());

        ResponseEntity<List<Product>> response = new ResponseEntity<>(products, HttpStatus.OK);
        return response;
    }

    public void updateProduct() {
    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ErrorDto> handleProductNotFoundExeption(ProductNotFoundException exception) {
//
//        ErrorDto errorDto = new ErrorDto();
//        errorDto.setMessage(exception.getMessage());
//
//        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
////        return null;
//    }

    // Limited to only the exceptions thrown from this controller
    // Controller Advices: Global

    // if this controller ever ends up throwing ProductNotFoundException.class
    // for any reason, don't throw that exception as is.
    // Instead call this method and return what this method is returning
}

// allProducts - X
// all APIs should be structured around resources
// GET  /products
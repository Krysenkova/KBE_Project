package com.example.kbeproject.product;

import com.example.kbeproject.valueObjects.ResponseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ProductService {

    private final ProductRepository productRepository;

    private RestTemplate restTemplate;

    @Autowired
    public ProductService(ProductRepository productRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public ResponseList sendProducts(List<Product> products) {
        System.out.println("products: " + products);
        ResponseList productList = restTemplate.postForObject("http://localhost:8081/api/vat",
                products, ResponseList.class);
        return productList;
    }
}

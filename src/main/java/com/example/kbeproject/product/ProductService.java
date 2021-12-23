package com.example.kbeproject.product;

import com.example.kbeproject.valueObjects.Storage;
import com.example.kbeproject.valueObjects.ResponseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ProductService {

    private final P_Repository productRepository;

    private RestTemplate restTemplate;

    @Autowired
    public ProductService(P_Repository productRepository, RestTemplate restTemplate) {
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

    public Storage getDeliveryInfoById(Long productId) {
        String url = "http://localhost:8082/api/storage/" + productId;
        System.out.println("url: " + url);
        Storage info = restTemplate.getForObject(url, Storage.class);
        return info;
    }
}

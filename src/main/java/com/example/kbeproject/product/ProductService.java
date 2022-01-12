package com.example.kbeproject.product;

import com.example.kbeproject.models.DeliveryInfoList;
import com.example.kbeproject.models.Storage;
import com.example.kbeproject.models.ResponseList;
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
        ResponseList productList = restTemplate.postForObject("http://localhost:8081/api/vat",
                products, ResponseList.class);
        return productList;
    }

    public DeliveryInfoList getDeliveryInfo(){
        return restTemplate.getForObject("http://localhost:8082/api/storage/all", DeliveryInfoList.class);
    }
    public Storage getDeliveryInfoById(Long productId) {
        String url = "http://localhost:8082/api/storage/" + productId;
        Storage info = restTemplate.getForObject(url, Storage.class);
        return info;
    }

    public void triggerDownload() {
        restTemplate.getForObject("http://localhost:8082/api/storage/download", String.class);
    }
}

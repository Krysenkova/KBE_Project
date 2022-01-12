package com.example.kbeproject.product;

import com.example.kbeproject.geocode.GeocodeController;
import com.example.kbeproject.geocode.GeocodeObject;
import com.example.kbeproject.geocode.GeocodeResult;
import com.example.kbeproject.geocode.GeocodeService;
import com.example.kbeproject.models.DeliveryInfoList;
import com.example.kbeproject.models.Storage;
import com.example.kbeproject.models.ResponseList;
import com.example.kbeproject.models.StringList;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
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

    public DeliveryInfoList getDeliveryInfo() {
        return restTemplate.getForObject("http://localhost:8082/api/storage/all", DeliveryInfoList.class);
    }

    public Storage getDeliveryInfoById(Long productId) {
        String url = "http://localhost:8082/api/storage/" + productId;
        Storage info = restTemplate.getForObject(url, Storage.class);
        return info;
    }

    public void getGeocode(DeliveryInfoList storageList) throws IOException {
        StringList formattedAddresses = restTemplate.postForObject("http://localhost:8080/api/geocode/addresse", storageList, StringList.class);
        if (formattedAddresses != null) {
            for (int i = 0; i < formattedAddresses.getStringList().size(); i++) {
                storageList.getStorageList().get(i).setLocation(formattedAddresses.getStringList().get(i));
            }
        }
        System.out.println(formattedAddresses);
    }


}



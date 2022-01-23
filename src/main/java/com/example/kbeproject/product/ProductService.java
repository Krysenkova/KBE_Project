package com.example.kbeproject.product;

import com.example.kbeproject.geocode.GeocodeResult;
import com.example.kbeproject.models.DeliveryInfoList;
import com.example.kbeproject.models.Storage;
import com.example.kbeproject.models.ResponseList;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URLEncoder;
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

    public String getFormattedAddress(Storage item) throws IOException{
        OkHttpClient client = new OkHttpClient();
        String encodedAddress = URLEncoder.encode(item.getLocation(), "UTF-8");
        Request request = new Request.Builder()
                .url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?language=en&address=" + encodedAddress)
                .get()
                .addHeader("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "1ae759c287msh779edf5552fed3bp1347bajsn63a12ee2cfe9"/*  Use your API Key here */)
                .build();
        ResponseBody responseBody = client.newCall(request).execute().body();
        ObjectMapper objectMapper = new ObjectMapper();
        GeocodeResult result = objectMapper.readValue(responseBody.string(), GeocodeResult.class);
        return result.getResults().get(0).getFormattedAddress();
    }
}

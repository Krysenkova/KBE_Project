package com.example.kbeproject.product;

import com.example.kbeproject.geocode.GeocodeResult;
import com.example.kbeproject.models.StorageInfoList;
import com.example.kbeproject.models.Price;
import com.example.kbeproject.models.StorageInfo;
import com.example.kbeproject.models.PriceList;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Component
@CacheConfig(cacheNames = "productCache")
public class ProductService {

    private final P_Repository productRepository;

    private RestTemplate restTemplate;

    @Autowired
    public ProductService(P_Repository productRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    @Cacheable(cacheNames = "products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public PriceList getPriceWithMwSt(List<Price> prices) {
        PriceList pricesList = restTemplate.postForObject("http://localhost:8081/api/mwst",
                prices, PriceList.class);
        return pricesList;
    }

    public StorageInfoList getDeliveryInfo(){
        return restTemplate.getForObject("http://localhost:8082/api/storage/all", StorageInfoList.class);
    }
    public StorageInfo getDeliveryInfoById(Long productId) {
        String url = "http://localhost:8082/api/storage/" + productId;
        StorageInfo info = restTemplate.getForObject(url, StorageInfo.class);
        return info;
    }

    public void triggerDownload() {
        restTemplate.getForObject("http://localhost:8082/api/storage/download", String.class);
    }

    public String getFormattedAddress(StorageInfo item) throws IOException{
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

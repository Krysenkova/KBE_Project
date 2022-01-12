package com.example.kbeproject.geocode;

import com.example.kbeproject.models.DeliveryInfoList;
import com.example.kbeproject.models.Storage;
import com.example.kbeproject.models.StringList;
import com.example.kbeproject.product.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/*
@RestController denotes class as Spring Component
@RequestMapping defines URL path for method & acceptable REST action (GET)
@RequestParam defines address variable that endpoint will accept

 */
@RestController
@RequestMapping(path = "api/geocode/addresse")
public class GeocodeController {

    private final GeocodeService geocodeService;

    @Autowired
    public GeocodeController(GeocodeService geocodeService) {
        this.geocodeService = geocodeService;
    }

    @RequestMapping(method = RequestMethod.GET )
    public StringList getGeocode(DeliveryInfoList list) throws IOException {
        List<String> productLocations = new ArrayList<>();
        for(Storage s : list.getStorageList()) {
            String formattedAddress = geocodeService.getFormattedAddress(s.getLocation());
            productLocations.add(formattedAddress);
        }
        return new StringList(productLocations) ;

 /*       OkHttpClient client = new OkHttpClient();
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        Request request = new Request.Builder()
                .url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?language=en&address=" + encodedAddress)
                .get()
                .addHeader("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "1ae759c287msh779edf5552fed3bp1347bajsn63a12ee2cfe9"*//*  Use your API Key here *//*)
                .build();
        ResponseBody responseBody = client.newCall(request).execute().body();
        ObjectMapper objectMapper = new ObjectMapper();
        GeocodeResult result = objectMapper.readValue(responseBody.string(), GeocodeResult.class);
        return result;*/
    }
}

package com.example.kbeproject.geocode;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
/*
@RestController: Spring annotation, denotes class as Spring Component
@RequestMapping: define URL-path for method & acceptable REST action -> GET
@RequestParam: defines address variable that endpoint will accept
In method:
    instantiate OkHttpClient object
    pass Google Maps API endpoint & encoded address to object
    add API key & host as headers
    handle response
 */

@RestController
public class GeocodeController {

    @RequestMapping(path = "/geocode", method = RequestMethod.GET )
    public GeocodeResult getGeocode(@RequestParam String address) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        Request request = new Request.Builder()
                .url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?language=en&address=" + encodedAddress)
                .get()
                .addHeader("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "1ae759c287msh779edf5552fed3bp1347bajsn63a12ee2cfe9"/*  Use your API Key here */)
                .build();

        ResponseBody responseBody = client.newCall(request).execute().body();
        ObjectMapper objectMapper = new ObjectMapper();
        GeocodeResult result = objectMapper.readValue(responseBody.string(), GeocodeResult.class);
        return result;
    }

}
package com.example.kbeproject.geocode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeResult {

    List<GeocodeObject> results;
    String status;

    public GeocodeResult() {
    }


    public List<GeocodeObject> getResults() {
        return results;
    }

}

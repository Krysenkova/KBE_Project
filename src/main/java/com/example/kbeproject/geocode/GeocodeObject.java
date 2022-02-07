package com.example.kbeproject.geocode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeObject {



    @JsonProperty("formatted_address")
    String formattedAddress;



    public GeocodeObject() {
    }


    public String getFormattedAddress() {
        return formattedAddress;
    }

}
package com.example.kbeproject.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class PriceList {
    @Schema(description = "list of the prices, used as an object to receive from other microservices")
    List<Price> priceList;

    public PriceList() {

    }
    public PriceList(List<Price> priceList) {
        this.priceList = priceList;
    }
}


package com.example.kbeproject.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class PriceList {
    List<Price> priceList;

    public PriceList() {

    }
    public PriceList(List<Price> priceList) {
        this.priceList = priceList;
    }
}


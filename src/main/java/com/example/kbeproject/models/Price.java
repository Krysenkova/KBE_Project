package com.example.kbeproject.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Price {

    private long itemId;
    private double price;

    public Price(long itemId, double price) {
        this.itemId = itemId;
        this.price = price;
    }

    public Price() {
    }
}

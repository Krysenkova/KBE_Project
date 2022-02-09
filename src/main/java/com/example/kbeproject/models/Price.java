package com.example.kbeproject.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Setter
@Getter
@ToString
public class Price {

    private UUID itemId;
    private double price;

    public Price(UUID itemId, double price) {
        this.itemId = itemId;
        this.price = price;
    }

    public Price() {
    }
}

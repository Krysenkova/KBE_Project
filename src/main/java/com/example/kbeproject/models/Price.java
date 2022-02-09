package com.example.kbeproject.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Setter
@Getter
@ToString
public class Price {
    @Schema(description = "unique id")
    private UUID itemId;
    @Schema(description = "price of the product")
    private double price;

    public Price(UUID itemId, double price) {
        this.itemId = itemId;
        this.price = price;
    }

    public Price() {
    }
}

package com.example.kbeproject.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter @Setter
@ToString
public class Storage {
    @Schema(description = "unique id")
    private UUID itemId;
    @Schema(description = "delivery time for particular product")
    private Long deliveryTime;
    @Schema(description = "how many products of the type are in the warehouse")
    private Integer amount;
    @Schema(description = "product location")
    private String location;

    public Storage(){

    }

    public Storage(UUID itemId, Long deliveryTime, Integer amount, String location) {
        this.itemId = itemId;
        this.deliveryTime = deliveryTime;
        this.amount = amount;
        this.location = location;
    }
}

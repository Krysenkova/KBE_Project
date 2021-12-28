package com.example.kbeproject.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Storage {

    private Long itemId;
    private Long deliveryTime;
    private Integer amount;
    private String location;

    public Storage(){

    }

    public Storage(Long itemId, Long deliveryTime, Integer amount, String location) {
        this.itemId = itemId;
        this.deliveryTime = deliveryTime;
        this.amount = amount;
        this.location = location;
    }
}

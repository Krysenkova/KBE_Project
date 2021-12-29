package com.example.kbeproject.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter
@ToString
public class ProductAllInfo {

    private Long itemId;
    private String name;
    private String description;
    private String material;
    private String colour;
    private String weight;
    private Double priceWithoutVat;
    private Double priceWithVat;

    private Long deliveryTime;
    private Integer amount;
    private String location;

    //TODO: Info von External API

    public ProductAllInfo() {
    }

    public ProductAllInfo(Long itemId, String name, String description, String material, String colour, String weight,
                          Double priceWithoutVat, Double priceWithVat, Long deliveryTime, Integer amount, String location) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.material = material;
        this.colour = colour;
        this.weight = weight;
        this.priceWithoutVat = priceWithoutVat;
        this.priceWithVat = priceWithVat;
        this.deliveryTime = deliveryTime;
        this.amount = amount;
        this.location = location;
    }
}
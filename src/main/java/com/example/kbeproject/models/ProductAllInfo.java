package com.example.kbeproject.models;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter
@ToString
public class ProductAllInfo {
    @CsvBindByName
    private Long itemId;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String description;
    @CsvBindByName
    private String material;
    @CsvBindByName
    private String colour;
    @CsvBindByName
    private String weight;
    @CsvBindByName
    private Double priceWithoutVat;
    @CsvBindByName
    private Double priceWithVat;
    @CsvBindByName
    private Long deliveryTime;
    @CsvBindByName
    private Integer amount;
    @CsvBindByName
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

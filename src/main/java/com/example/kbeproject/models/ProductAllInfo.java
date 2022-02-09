package com.example.kbeproject.models;

import com.opencsv.bean.CsvBindByName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;


@Getter @Setter
public class ProductAllInfo {
    @CsvBindByName
    @Schema(description = "unique id")
    private UUID itemId;
    @Schema(description = "name of the product")
    @CsvBindByName
    private String name;
    @Schema(description = "description of the product")
    @CsvBindByName
    private String description;
    @Schema(description = "what is the product made of")
    @CsvBindByName
    private String material;
    @Schema(description = "color of the product")
    @CsvBindByName
    private String colour;
    @Schema(description = "weight of the product")
    @CsvBindByName
    private String weight;
    @Schema(description = "price without MwSt in EUR")
    @CsvBindByName
    private Double priceWithoutVat;
    @Schema(description = "price with MwSt in EUR")
    @CsvBindByName
    private Double priceWithVat;
    @Schema(description = "delivery time for particular product")
    @CsvBindByName
    private Long deliveryTime;
    @Schema(description = "how many products of the type are in the warehouse")
    @CsvBindByName
    private Integer amount;
    @Schema(description = "location of the product")
    @CsvBindByName
    private String location;

    public ProductAllInfo() {
    }

    public ProductAllInfo(UUID itemId, String name, String description, String material, String colour, String weight,
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

    @Override
    public String toString() {
        return itemId + "; " + name + "; " +
                description + "; " + material + "; "
                + colour + "; " + weight + "; " +
                + priceWithoutVat + "; " + priceWithVat + "; "
                + deliveryTime + "; "  + amount + "; "
                + location;
    }
}

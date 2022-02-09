package com.example.kbeproject.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Entity
@Table(name = "product")
@Getter @Setter
@ToString
public class Product {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "item_id")
    @Schema(description = "unique id")
    private UUID itemId;
    @Column(name = "name")
    @Schema(description = "name of product")
    private String name;
    @Schema(description = "description of the product")
    @Column(name = "description")
    private String description;
    @Schema(description = "what is product made of")
    @Column(name = "material")
    private String material;
    @Schema(description = "main color")
    @Column(name = "colour")
    private String colour;
    @Schema(description = "weigh of the product in kg.")
    @Column(name = "weight")
    private String weight;
    @Schema(description = "price without mwst")
    @Column(name = "price_without_vat")
    @Positive(message = "Price can't be null or negative")
    private Double priceWithoutVat;

    public Product() {
    }

    /**
     * constructor without id
     */
    public Product(String name, String description, String material, String colour, String weight, Double priceWithoutVat) {
        this.name = name;
        this.description = description;
        this.material = material;
        this.colour = colour;
        this.weight = weight;
        this.priceWithoutVat = priceWithoutVat;
    }
}

package com.example.kbeproject.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "product")
@Getter @Setter
@ToString
public class Product {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    //sequence generator? do we need it?
    @Column(name = "item_id")   //do not know if we really need column annotations
    private UUID itemId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "material")
    private String material;
    @Column(name = "colour")
    private String colour;
    @Column(name = "weight")
    private String weight;
    @Column(name = "price_without_vat")
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

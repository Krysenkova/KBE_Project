package com.example.kbeproject.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cache.annotation.CacheConfig;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter @Setter
@ToString
public class Product {

    @Id
    //sequence generator? do we need it?
    //@GeneratedValue(generator = "uuid")
    @Column(name = "item_id")   //do not know if we really need column annotations
    private Long itemId;
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
    @Transient
    private Double priceWithVat;

    public Product() {
    }

    /**
     * constructor without id
     */
    public Product(Long itemId, String name, String description, String material, String colour, String weight, Double priceWithoutVat, Double priceWithVat) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.material = material;
        this.colour = colour;
        this.weight = weight;
        this.priceWithoutVat = priceWithoutVat;
        this.priceWithVat = priceWithVat;
    }
}

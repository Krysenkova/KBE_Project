package com.example.kbeproject.product;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication


//@Entity
//@Table(name="Product")
public class Product {

    //@Id
   // @GeneratedValue(generator = "uuid")
    private Long itemId;
    private String name;
    private String description;
    private String material;
    private String colour;
    private String weight;
    private Long timeOfDelivery;
    private Double priceWithoutVat;

    public Product() {    }

    /**
     * constructor without id (id will be automatically generated in database)
     */
    public Product(String name, String description, String material, String colour, String weight, Long timeOfDelivery, Double priceWithoutVat) {
        this.name = name;
        this.description = description;
        this.material = material;
        this.colour = colour;
        this.weight = weight;
        this.timeOfDelivery = timeOfDelivery;
        this.priceWithoutVat = priceWithoutVat;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Long getTimeOfDelivery() {
        return timeOfDelivery;
    }

    public void setTimeOfDelivery(Long timeOfDelivery) {
        this.timeOfDelivery = timeOfDelivery;
    }

    public Double getPriceWithoutVat() {
        return priceWithoutVat;
    }

    public void setPriceWithoutVat(Double priceWithoutVat) {
        this.priceWithoutVat = priceWithoutVat;
    }

    @Override
    public String toString() {
        return "Product{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", material='" + material + '\'' +
                ", colour='" + colour + '\'' +
                ", weight='" + weight + '\'' +
                ", timeOfDelivery=" + timeOfDelivery +
                ", priceWithoutVat=" + priceWithoutVat +
                '}';
    }

}
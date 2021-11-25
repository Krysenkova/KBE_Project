package com.example.kbeproject.product;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {

    //for now some hardcoded products - later data from database
    public List<Product> getProducts() {
        return List.of(new Product("Christmas tree", "Nice christmas tree for nice holiday mood", "wood", "brown and green", "10 kg.", 48 * 3600000L, 50.0),
                        new Product("Set of candles", "3 candles with apple and cinnamon aroma", "wax", "beige", "0.2 kg.", 24 * 3600000L, 10.0));
    }
}
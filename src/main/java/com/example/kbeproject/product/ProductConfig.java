package com.example.kbeproject.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    CommandLineRunner commandLineRunner(ProductRepository repository){
        return args -> {

        };
    }
}

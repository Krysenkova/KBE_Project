package com.example.kbeproject.models;

import com.example.kbeproject.product.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class ResponseList {
    List<Product> productList;

    public ResponseList() {

    }
    public ResponseList(List<Product> productList) {
        this.productList = productList;
    }
}

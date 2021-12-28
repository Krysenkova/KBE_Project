package com.example.kbeproject.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DeliveryInfoList {
    List<Storage> storageList;

    public DeliveryInfoList() {

    }
    public DeliveryInfoList(List<Storage> storageList) {
        this.storageList = storageList;
    }
}

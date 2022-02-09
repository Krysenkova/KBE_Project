package com.example.kbeproject.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DeliveryInfoList {

    @Schema(description = "list of delivery information, used to get response from Storage Service")
    List<Storage> storageList;

    public DeliveryInfoList() {

    }
    public DeliveryInfoList(List<Storage> storageList) {
        this.storageList = storageList;
    }
}

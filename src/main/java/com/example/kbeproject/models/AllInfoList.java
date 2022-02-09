package com.example.kbeproject.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AllInfoList {

    @Schema(description = "all product information list")
    private List<ProductAllInfo> allInfoList;

    public AllInfoList(List<ProductAllInfo> allInfoList) {
        this.allInfoList = allInfoList;
    }

    public AllInfoList() {
    }
}

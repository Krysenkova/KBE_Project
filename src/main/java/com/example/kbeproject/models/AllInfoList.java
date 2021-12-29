package com.example.kbeproject.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AllInfoList {
    private List<ProductAllInfo> allInfoList;


    public AllInfoList(List<ProductAllInfo> allInfoList) {
        this.allInfoList = allInfoList;
    }

    public AllInfoList() {
    }
}

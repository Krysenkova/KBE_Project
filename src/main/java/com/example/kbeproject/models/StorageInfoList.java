package com.example.kbeproject.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class StorageInfoList {

    @Schema(description = "list of storage information, used to get response from Storage Service")
    List<StorageInfo> storageInfoList;

    public StorageInfoList() {

    }
    public StorageInfoList(List<StorageInfo> storageInfoList) {
        this.storageInfoList = storageInfoList;
    }
}

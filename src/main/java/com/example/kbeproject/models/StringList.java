package com.example.kbeproject.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class StringList {
    private List<String> stringList;

    public StringList() {
    }

    public StringList(List<String> stringList) {
        this.stringList = stringList;
    }
}

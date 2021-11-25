package com.example.kbeproject.calculator;

public class Calculator {
    //private Double price;
    private final double MWST = 0.19;


    public Calculator() {
        // this.price = price;
    }

    public double calculateMwSt(Double price) {
        return price * MWST;
    }

    //TODO do we need this?
    // public double calculateEndprice() {
    //     return price + calculateMwSt();
    // }


    public Double getMwSt() {
        return MWST;
    }

    @Override
    public String toString() {
        return "Calculator{" +
                ", MWST=" + MWST +
                '}';
    }
}

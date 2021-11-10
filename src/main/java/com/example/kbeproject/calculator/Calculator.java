package com.example.kbeproject.calculator;

public class Calculator {
    private Double price;
    private Double mwSt;


    public Calculator(Double price, Double mwSt) {
        this.price = price;
        this.mwSt = mwSt;
    }

    public double calculateEndprice() {
        return price + price * mwSt;
    }

    public Double getPrice() {
        return price;
    }

    public Double getMwSt() {
        return mwSt;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setMwSt(Double mwSt) {
        this.mwSt = mwSt;
    }

    @Override
    public String toString() {
        return "Calculator{" +
                "price=" + price +
                ", mwST=" + mwSt +
                '}';
    }
}

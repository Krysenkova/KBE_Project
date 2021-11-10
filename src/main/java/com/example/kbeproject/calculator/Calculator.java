package com.example.kbeproject.calculator;

public class Calculator {
    private double price;
    private double mwSt;


    public Calculator(double price, double mwSt) {
        this.price = price;
        this.mwSt = mwSt;
    }

    public double calculateEndprice() {
        return price + price * mwSt;
    }

    public double getPrice() {
        return price;
    }

    public double getMwSt() {
        return mwSt;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMwSt(double mwSt) {
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

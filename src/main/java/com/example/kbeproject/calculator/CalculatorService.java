package com.example.kbeproject.calculator;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class CalculatorService {
    private Calculator calc;

    public double calculatePrice(double price, double mwSt) {
        calc = new Calculator(price, mwSt);
        //return calc.calculateEndprice();
        return 55.5;
    }
}

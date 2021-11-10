package com.example.kbeproject.calculator;
import org.springframework.stereotype.Component;


@Component
public class CalculatorService {
    private Calculator calc;

    public Double calculateMwSt(Double price, Double mwSt) {
        calc = new Calculator(price, mwSt);
        return calc.calculateMwSt();
    }
}

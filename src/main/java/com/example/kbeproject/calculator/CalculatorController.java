package com.example.kbeproject.calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/endprice")
public class CalculatorController {

    private final CalculatorService calcServ;

    @Autowired
    public CalculatorController(CalculatorService calcServ) {
        this.calcServ = calcServ;
    }

    @GetMapping
    public double endpriceCalculate(double price, double mwSt){
        return calcServ.calculatePrice(price, mwSt);
    }
}

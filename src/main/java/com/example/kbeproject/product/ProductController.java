package com.example.kbeproject.product;

import com.example.kbeproject.models.DeliveryInfoList;
import com.example.kbeproject.models.Storage;
import com.example.kbeproject.models.ResponseList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/products")
public class ProductController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        logger.trace("Getting Products in ProductController");
        return productService.getProducts();
    }

    @PostMapping("/list")
    public ResponseList sendProducts(@RequestBody List<Product> products){
       return productService.sendProducts(products);
    }


    @GetMapping("/delivery_info")
    public DeliveryInfoList getDeliveryInfo(){
       return productService.getDeliveryInfo();
    }

    @GetMapping("/delivery_info/{id}")
    public Storage getDeliveryInfoById(@PathVariable("id") Long productId){
        return productService.getDeliveryInfoById(productId);
    }
}

package com.example.kbeproject.product;

import com.example.kbeproject.models.*;
import com.example.kbeproject.utils.CsvWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    public ResponseList sendProducts(@RequestBody List<Product> products) {
        return productService.sendProducts(products);
    }

    @GetMapping("/delivery_info")
    public DeliveryInfoList getDeliveryInfo() throws IOException {
        DeliveryInfoList list = productService.getDeliveryInfo();
        for(Storage item: list.getStorageList()){
            item.setLocation(productService.getFormattedAddress(item));
        }
        return list;
    }

    @GetMapping("/delivery_info/{id}")
    public Storage getDeliveryInfoById(@PathVariable("id") Long productId) throws IOException {
        Storage storage = productService.getDeliveryInfoById(productId);
        storage.setLocation(productService.getFormattedAddress(storage));
        return storage;
    }

    @GetMapping("/export")
    public void exportToCSV() throws IOException {
        CsvWriter writer = new CsvWriter();
        List<Product> l = productService.getProducts();
        ResponseList rl = productService.sendProducts(l);
        DeliveryInfoList dil = productService.getDeliveryInfo();
        List<String> stringList = new ArrayList<>();
        for (Product item : rl.getProductList()) {
            for (Storage storage : dil.getStorageList()) {
                if (item.getItemId().equals(storage.getItemId())) {
                    ProductAllInfo p = new ProductAllInfo(item.getItemId(), item.getName(), item.getDescription(), item.getMaterial(),
                            item.getColour(), item.getWeight(), item.getPriceWithoutVat(), item.getPriceWithVat(),
                            storage.getDeliveryTime(), storage.getAmount(), storage.getLocation());
                    stringList.add(p.toString());
                }
            }

        }
        writer.writeToCsvFile(stringList, new File("all_info.csv"));
    }
}

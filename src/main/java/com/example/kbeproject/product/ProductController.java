package com.example.kbeproject.product;

import com.example.kbeproject.models.*;
import com.example.kbeproject.utils.CsvWriter;
import com.example.kbeproject.upload.FileTransferServiceImpl;
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
    private final FileTransferServiceImpl transfer;

    @Autowired
    public ProductController(ProductService productService, FileTransferServiceImpl transfer) {
        this.productService = productService;
        this.transfer = transfer;
    }

    @GetMapping
    public List<Product> getProducts() {
        logger.trace("Getting Products in ProductController");
        return productService.getProducts();
    }

    @PostMapping("/prices")
    public PriceList getPriceWithMwSt(@RequestBody List<Price> prices) {
        return productService.sendForPriceWithMwSt(prices);
    }

    @GetMapping("/delivery_info")
    public DeliveryInfoList getDeliveryInfo() throws IOException {
        DeliveryInfoList list = productService.getDeliveryInfo();
        for (Storage item : list.getStorageList()) {
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
        List<Price> p = new ArrayList<>();
        for (Product item : l) {
            p.add(new Price(item.getItemId(), item.getPriceWithoutVat()));
        }
        List<Price> prices = productService.sendForPriceWithMwSt(p).getPriceList();
        List<Storage> storage = productService.getDeliveryInfo().getStorageList();
        List<String> stringList = new ArrayList<>();
        int index = 0;
        for (Product item : l) {
            for (Storage s : storage) {
                if (item.getItemId().equals(s.getItemId())) {
                    ProductAllInfo allInfo = new ProductAllInfo(item.getItemId(), item.getName(), item.getDescription(), item.getMaterial(),
                            item.getColour(), item.getWeight(), item.getPriceWithoutVat(), prices.get(index).getPrice(),
                            s.getDeliveryTime(), s.getAmount(), s.getLocation());
                    stringList.add(allInfo.toString());
                }
            }
            index++;
        }
        writer.writeToCsvFile(stringList, new File("all_info.csv"));
        transfer.uploadFile("all_info.csv", "all_info.csv");
        productService.triggerDownload();
    }
}

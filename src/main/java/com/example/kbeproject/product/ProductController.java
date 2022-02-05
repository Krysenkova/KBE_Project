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
        logger.info("Getting Products");
        return productService.getProducts();
    }

    @PostMapping("/prices")
    public PriceList getPriceWithMwSt(@RequestBody List<Price> prices) {
        logger.info("Getting price with Mehrwertsteuerr");
        return productService.getPriceWithMwSt(prices);
    }

    @GetMapping("/delivery_info")
    public DeliveryInfoList getDeliveryInfo() throws IOException {
        logger.info("Getting Info from Storage");
        DeliveryInfoList list = productService.getDeliveryInfo();
        for (Storage item : list.getStorageList()) {
            item.setLocation(productService.getFormattedAddress(item));
        }
        return list;
    }

    @GetMapping("/delivery_info/{id}")
    public Storage getDeliveryInfoById(@PathVariable("id") Long productId) throws IOException {
        logger.info("Getting Info from Storage for product with id " + productId);
        Storage storage = productService.getDeliveryInfoById(productId);
        storage.setLocation(productService.getFormattedAddress(storage));
        return storage;
    }

    @GetMapping("/export")
    public void exportData() throws IOException {
        logger.info("Start exporting products info");
        List<Product> products = productService.getProducts();
        List<Price> pricesWithoutMwSt = new ArrayList<>();
        for (Product item : products) {
            pricesWithoutMwSt.add(new Price(item.getItemId(), item.getPriceWithoutVat()));
        }
        List<Price> pricesWithMwSt = productService.getPriceWithMwSt(pricesWithoutMwSt).getPriceList();
        List<Storage> storage = getDeliveryInfo().getStorageList();
        List<String> stringList = prepareDataForCSV(products, pricesWithMwSt, storage);
        writeDataToCSV(stringList);
        uploadFileToSftpServer();
        productService.triggerDownload();
    }

    private List<String> prepareDataForCSV(List<Product> products, List<Price> prices, List<Storage> storage) {
        List<String> stringList = new ArrayList<>();
        int index = 0;
        for (Product item : products) {
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
        logger.info("Data is prepared to be written to CSV file");
        return stringList;
    }

    private void writeDataToCSV(List<String> stringList) throws IOException {
        logger.info("Writing data to CSV file");
        CsvWriter writer = new CsvWriter();
        writer.writeToCsvFile(stringList, new File("all_info.csv"));
    }

    private void uploadFileToSftpServer() {
        if (transfer.uploadFile("all_info.csv", "all_info.csv")) {
            logger.info("File is uploaded to sftp server successfully");
        }
        else logger.error("The problem occurred while uploading file to sftp server");
    }
}

package com.example.kbeproject.product;

import com.example.kbeproject.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public DeliveryInfoList getDeliveryInfo() {
        return productService.getDeliveryInfo();
    }

    @GetMapping("/delivery_info/{id}")
    public Storage getDeliveryInfoById(@PathVariable("id") Long productId) {
        return productService.getDeliveryInfoById(productId);
    }

    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=info.csv";
        response.setHeader(headerKey, headerValue);
        List<Product> l = productService.getProducts();
        ResponseList rl = productService.sendProducts(l);
        DeliveryInfoList dil = productService.getDeliveryInfo();
        AllInfoList allInfo = new AllInfoList();
        for (Product item : rl.getProductList()) {
            for (Storage stor : dil.getStorageList()) {
                if (item.getItemId().equals(stor.getItemId())) {
                    ProductAllInfo p = new ProductAllInfo(item.getItemId(), item.getName(), item.getDescription(), item.getMaterial(),
                            item.getColour(), item.getWeight(), item.getPriceWithoutVat(), item.getPriceWithVat(),
                            stor.getDeliveryTime(), stor.getAmount(), stor.getLocation(), item.getFormattedAddress());
                    allInfo.getAllInfoList().add(p);
                }
            }

        }

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Product ID", "Name", "Description", "Material", "Colour", "Weight", "Price Without VAT",
                                "Price with VAT", "Delivery Time", "Amount", "Location"};
       /* String[] nameMapping = {"item_id", "name", "description", "material", "colour", "weight", "price_without_vat",
                                "price_with_vat", "delivery_time", "amount", "product_location"};*/

        csvWriter.writeHeader(csvHeader);

        for (ProductAllInfo prod : allInfo.getAllInfoList()) {
            csvWriter.write(prod);
        }
        csvWriter.close();
    }


}

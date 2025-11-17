package com.ecommerce.price.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.price.service.LowerPriceService;
import com.ecommerce.price.vo.Product;

@RestController
@RequestMapping("/api")
public class LowestPriceController {
    @Autowired
    private LowerPriceService lowestPriceService;

    @GetMapping("/product")
    public Set getZsetValue (@RequestParam("key") String key){
        return lowestPriceService.GetZsetValue(key);
    }
    
    @PutMapping("/product")
    public int setNewProduct(@RequestBody Product newProduct){
        return lowestPriceService.setNewProduct(newProduct);
    }
}

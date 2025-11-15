package com.ecommerce.price.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.price.service.LowerPriceService;

@RestController
@RequestMapping("/")
public class LowestPriceController {
    @Autowired
    private LowerPriceService lowestPriceService;

    @GetMapping("/getZSETValue")
    public Set getZsetValue (String key){
        return lowestPriceService.GetZsetValue(key);
    }
}

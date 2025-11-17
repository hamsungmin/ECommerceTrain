package com.ecommerce.price.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.price.service.LowerPriceService;
import com.ecommerce.price.vo.Keyword;
import com.ecommerce.price.vo.Product;
import com.ecommerce.price.vo.ProductGrp;

@RestController
@RequestMapping("/api")
public class LowestPriceController {
    @Autowired
    private LowerPriceService lowestPriceService;

    @GetMapping("/product")
    public Set getZsetValue (@RequestParam("key") String key){
        return lowestPriceService.GetZsetValue(key);
    }
    @GetMapping("/product1")
    public Set GetZsetValueWithStatus (String key){
        try {
            return lowestPriceService.GetZsetValue(key);
        }
        catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }
    
    @PutMapping("/product")
    public int setNewProduct(@RequestBody Product newProduct){
        return lowestPriceService.setNewProduct(newProduct);
    }
    
    @PutMapping("/productGroup")
    public int setNewProductGroup(@RequestBody ProductGrp newProductGrp){
        return lowestPriceService.setNewProductGrp(newProductGrp);
    }
    
    @PutMapping("/productGroupToKeyword")
    public int setNewProductGrpToKeyword(@RequestParam("keyworkd") String keyword, @RequestParam("productGrpId") String productGrpId, @RequestParam("score") double score){
        return lowestPriceService.setNewProductGrpToKeyword(keyword,productGrpId, score);
    }
    
    @GetMapping("/productPrice/lowest")
    public Keyword getLowestPriceProductByKeyword(String keyword) {
    	return lowestPriceService.getLowestPriceProductByKeyword(keyword);
    }
    	
    
}

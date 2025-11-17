package com.ecommerce.price.service;

import java.util.Set;

import com.ecommerce.price.vo.Product;

public interface LowerPriceService {
    Set GetZsetValue(String key);
    
    int setNewProduct(Product newProduct);
}

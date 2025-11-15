package com.ecommerce.price.vo;

import lombok.Data;
import java.util.List;

@Data
public class ProductGrp {
    private String prodGroId; //FPG0001
    private List<Product> productList; //[{GUID, 25000}, {GUID, 30000}]
    
}
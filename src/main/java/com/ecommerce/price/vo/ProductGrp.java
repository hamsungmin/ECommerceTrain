package com.ecommerce.price.vo;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class ProductGrp {
    private String prodGroId; //FPG0001 -> 상품
    private List<Product> productList; //[{GUID, 25000}, {GUID, 30000}]
    
}
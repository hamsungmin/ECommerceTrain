package com.ecommerce.price.vo;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Product {
	private String prodGrpId; // FPG0001
    private String productId; // GUID
    private int price; //25000 (won)
    
}
package com.ecommerce.price.service;

import java.util.Set;

import com.ecommerce.price.vo.Keyword;
import com.ecommerce.price.vo.Product;
import com.ecommerce.price.vo.ProductGrp;

public interface LowerPriceService {
    Set GetZsetValue(String key);
    
    int setNewProduct(Product newProduct);

	int setNewProductGrp(ProductGrp newProductGrp);
	
	int setNewProductGrpToKeyword(String keyword, String productGrpId, double score);
	
	Keyword getLowestPriceProductByKeyword(String keyword);
}

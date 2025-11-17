package com.ecommerce.price.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ecommerce.price.vo.Product;

@Service
public class LowerPriceServiceImpl implements LowerPriceService {
    @Autowired
    private RedisTemplate myProdPriceRedis;
    
    public Set GetZsetValue(String key)  {
        Set myTempSet = new HashSet();
        myTempSet = myProdPriceRedis.opsForZSet().rangeWithScores(key, 0, 9);
        return myTempSet;
    }

	@Override
	public int setNewProduct(Product newProduct) {
		int rank = 0;
		myProdPriceRedis.opsForZSet().add(newProduct.getProdGrpId(), newProduct.getProductId(), newProduct.getPrice());
		rank = myProdPriceRedis.opsForZSet().rank(newProduct.getProdGrpId(), newProduct.getProductId()).intValue();
		return rank;
	};

}

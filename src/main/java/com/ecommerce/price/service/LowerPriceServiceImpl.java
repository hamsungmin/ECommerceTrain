package com.ecommerce.price.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@service
public class LowerPriceServiceImpl implements LowerPriceService {
    @Autowired
    private RedisTemplate myProdPriceRedis;

    
    public Set getZsetValue(String key){
        Set myTempSet  new HashSet<>();
        myTempSet = myProdPriceRedis.opsForZSet().rageWithScores(key, 0, 9);
        return myTempSet;

    }

}

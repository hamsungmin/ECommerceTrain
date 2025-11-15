package com.ecommerce.price.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class LowerPriceServiceImpl implements LowerPriceService {
    @Autowired
    private RedisTemplate myProdPriceRedis;
    
    public Set GetZsetValue(String key)  {
        Set myTempSet = new HashSet();
        myTempSet = myProdPriceRedis.opsForZSet().rangeWithScores(key, 0, 9);
        return myTempSet;
    };

}

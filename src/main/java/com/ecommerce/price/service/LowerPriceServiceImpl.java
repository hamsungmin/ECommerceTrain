package com.ecommerce.price.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ecommerce.price.vo.Keyword;
import com.ecommerce.price.vo.Product;
import com.ecommerce.price.vo.ProductGrp;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	}

	@Override
	public int setNewProductGrp(ProductGrp newProductGrp) {
		List<Product> product = newProductGrp.getProductList();
		String productId = product.get(0).getProductId();
		int price = product.get(0).getPrice(); // 하나만 있다는 가정
		myProdPriceRedis.opsForZSet().add(newProductGrp.getProdGroId(), productId, price);
		return myProdPriceRedis.opsForZSet().zCard(newProductGrp.getProdGroId()).intValue();
	};
	
	public int setNewProductGrpToKeyword(String keyword, String productGrpId, double score) {
		myProdPriceRedis.opsForZSet().add(keyword, productGrpId, score);
		int rank = myProdPriceRedis.opsForZSet().rank(keyword, productGrpId).intValue();
		return rank;
	}
	
	public Keyword getLowestPriceProductByKeyword(String keyword) {
		Keyword inputKeyword = new Keyword();
		List<ProductGrp> tempProdGrp = new ArrayList<>();
		// keyword를 통해  productGroup 가져오기 (10개)
		tempProdGrp = getProdGrpUsingKeyword(keyword);
		
		inputKeyword.setKeyword(keyword);
		inputKeyword.setProductGrpList(tempProdGrp);
		// 가져온 정보들을 REturn 할 Object에 넣기
		// 해당 Object return
		
		return inputKeyword;
	}
	//10개 ProductGrpId로 loop
	public List<ProductGrp> getProdGrpUsingKeyword(String keyword) {
		List<ProductGrp> returnInfo = new ArrayList<>();
		// input 받은 keyword 로 productGrpId를 조회
		List<String> prodGrpIdList = new ArrayList<>();
		prodGrpIdList = List.copyOf(myProdPriceRedis.opsForZSet().reverseRange(keyword, 0, 9)); 
		List<Product> tempProdList = new ArrayList<>();
		
		for(final String productGrpId : prodGrpIdList) {
			// loop 타면서 productGrpId로 product:price 가져오기 (10개)
			ProductGrp tempProdGrp = new ProductGrp();
			
			Set prodAndPriceList = new HashSet();
			prodAndPriceList = myProdPriceRedis.opsForZSet().rangeWithScores(productGrpId, 0, 9);
			
			Iterator<Object> prodPriceObj = prodAndPriceList.iterator();
			// loop다면서 Product Obj bind (10개)
			while(prodPriceObj.hasNext()) {
				ObjectMapper objMapper = new ObjectMapper();
				// {"value":001-0001-1111}, {"score":11000} 
				Map<String, Object> prodPriceMap = objMapper.convertValue(prodPriceObj.next(), Map.class);
				Product tempProduct = new Product();
				// Product Obj bind
				tempProduct.setProductId(prodPriceMap.get("value").toString()); //prod_id
				tempProduct.setPrice(Double.valueOf(prodPriceMap.get("score").toString()).intValue()); //es 검색된 score
				tempProdList.add(tempProduct);
			}
			// 10개 Product price 입력완료
			tempProdGrp.setProdGroId(productGrpId);
			tempProdGrp.setProductList(tempProdList);
			returnInfo.add(tempProdGrp);
		}
		
		return returnInfo;
	}
}

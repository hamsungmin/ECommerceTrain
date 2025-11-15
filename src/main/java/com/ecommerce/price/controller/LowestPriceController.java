package com.ecommerce.price.controller;

@RestController
@RequestMapping("/")
public class LowestPriceController {
    @Autowired
    private LowerstPriceService lowestPriceService;

    @GetMaping("/getZSETValue")
    public Set getZsetValue (Stinrg key){
        return lowestPriceService.getZsetValue(key);
    }
}

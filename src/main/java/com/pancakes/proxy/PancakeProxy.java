package com.pancakes.proxy;

import com.pancakes.config.Config;
import com.pancakes.model.Product;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="eatwellProxy", url="https://mreatwell.com", configuration = Config.class)
@Headers({"accept: */*",
        "accept-language: en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7",
        "referer: https://mreatwell.com/collections/x-holiday-inn-express/products/x-holiday-inn-express-pancakesuit-w-warming-pocket-hoodie",
        "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36"})

public interface PancakeProxy {

    @GetMapping("/products/x-holiday-inn-express-pancakesuit-w-warming-pocket-hoodie.js")
    Product getProducts();
}

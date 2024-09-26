package com.pancakes.config;

import feign.Response;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

@Configuration
@EnableFeignClients (basePackages = "com.pancakes.proxy")
public class Config {

    //custom decoder to handle text/javascript
//    @Bean
//    public Decoder feignDecoder() {
//        return new SpringDecoder(messageConvertersObjectFactory) {
//            @Override
//            public Object decode(Response response, Type type) throws IOException {
//                Collection<String> contentType = response.headers().get("Content-Type");
//
//                if (contentType != null && !contentType.contains("application/json")) {
//                    //treat javascript as json
//                    response = Response.builder()
//                            .request(response.request())
//                            .status(response.status())
//                            .headers(response.headers())
//                            .body(response.body())
//                            .build();
//                }
//                return super.decode(response, type);
//            }
//        };
//    }

    @Bean
    public MappingJackson2HttpMessageConverter converter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.parseMediaType("text/javascript; charset=utf-8")));

        return converter;
    }

}

package com.capitole.victorpacheco.infrastructure.configuration;

import com.capitole.victorpacheco.infrastructure.httpclient.ProductHttpClient;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {


    @Bean
    public ProductHttpClient productHttpClient(HttpClientOptions httpClientOptions){

        return Feign.builder()
            .client(new OkHttpClient())
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger(ProductHttpClient.class))
            .logLevel(Logger.Level.FULL)
            .options(new Request.Options(
                httpClientOptions.getConnectTimeout(),
                httpClientOptions.getConnectTimeoutUnit(),
                httpClientOptions.getReadTimeout(),
                httpClientOptions.getReadTimeoutUnit(),
                httpClientOptions.getFollowRedirects()
            ))
            .retryer(Retryer.NEVER_RETRY)
            .target(ProductHttpClient.class, httpClientOptions.getUrl());
    }

}

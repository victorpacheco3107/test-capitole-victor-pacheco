package com.capitole.victorpacheco.infrastructure.configuration;

import com.capitole.victorpacheco.infrastructure.productclient.ProductClient;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
@EnableScheduling
@Slf4j
public class ProductClientConfiguration {


    @Bean
    public ProductClient productHttpClient(HttpClientOptions httpClientOptions){
        log.warn("Client options: {}", httpClientOptions);
        return Feign.builder()
            .client(new OkHttpClient())
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger(ProductClient.class))
            .logLevel(Logger.Level.FULL)
            .options(new Request.Options(
                httpClientOptions.getConnectTimeout(),
                httpClientOptions.getConnectTimeoutUnit(),
                httpClientOptions.getReadTimeout(),
                httpClientOptions.getReadTimeoutUnit(),
                httpClientOptions.getFollowRedirects()
            ))
            .retryer(Retryer.NEVER_RETRY)
            .target(ProductClient.class, httpClientOptions.getUrl());
    }

    @Scheduled(fixedRateString = "${cache.duration}")
    @CacheEvict(value = "productDetail", allEntries = true)
    public void deleteCacheProductDetail() {
        log.info("Deleting cache productDetail");
    }

    @Scheduled(fixedRateString = "${cache.duration}")
    @CacheEvict(value = "similarIds", allEntries = true)
    public void deleteCacheSimilarIds() {
        log.info("Deleting cache similarIds");
    }

}

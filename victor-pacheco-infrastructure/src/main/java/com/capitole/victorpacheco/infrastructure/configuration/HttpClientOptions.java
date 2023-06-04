package com.capitole.victorpacheco.infrastructure.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "http-client")
public class HttpClientOptions {
    private String url;
    private Long connectTimeout;
    private TimeUnit connectTimeoutUnit;
    private Long readTimeout;
    private TimeUnit readTimeoutUnit;
    private Boolean followRedirects;
}

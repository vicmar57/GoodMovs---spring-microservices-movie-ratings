package com.vicmar57.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class MovieCatalogServiceApplication {
    @Bean // executes this method once and assigns the return statement to the return type to be availible to classes. singleton instance. bean is a producer
    @LoadBalanced // go through eureka service. does (client-side) service discovery in a load balanced way.
    //the URL i'm giving you is only a "hint" of where you need to go
    //client side load balancing  means the microservice itself picks the other microservice to communicate with
    public RestTemplate getRestTemplate(){
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setConnectTimeout(3000);
        return new RestTemplate(httpComponentsClientHttpRequestFactory);
    }

//    @Bean
//    public WebClient.Builder getWebClientBuilder(){
//        return WebClient.builder();
//    }

    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogServiceApplication.class, args);
    }


}

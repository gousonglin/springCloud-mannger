package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients  //开启feign的负载均衡
public class SpringCloudConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsumerApplication.class, args);
    }
}

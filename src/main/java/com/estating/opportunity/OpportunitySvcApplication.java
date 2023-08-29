package com.estating.opportunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@ConfigurationPropertiesScan("com.estating.opportunity.config")
public class OpportunitySvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpportunitySvcApplication.class, args);
    }

}

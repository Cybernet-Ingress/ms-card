package com.example.ms.card;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsCardApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCardApplication.class, args);
    }
}

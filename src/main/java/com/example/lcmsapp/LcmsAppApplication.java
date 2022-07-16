package com.example.lcmsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LcmsAppApplication {

    //o'z tizimizdan turib boshqa apiga ulanish
    //1.Resttemplate
    //2.FeigntClient -> dependency


    public static void main(String[] args) {
        SpringApplication.run(LcmsAppApplication.class, args);
    }

}

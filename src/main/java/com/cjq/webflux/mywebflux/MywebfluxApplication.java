package com.cjq.webflux.mywebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cjq.webflux.mywebflux.*"})
public class MywebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(MywebfluxApplication.class, args);
    }

}

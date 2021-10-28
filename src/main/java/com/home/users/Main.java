package com.home.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main .class, args);
    }
}

package com.anobel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableTransactionManagement
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class,args);

    }

}
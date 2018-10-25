package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
class JPAApplication {

    public static void main(String[] args) {
        //System.setProperty("spring.profiles.active","mysql");
        SpringApplication.run(JPAApplication.class,args);
    }

}

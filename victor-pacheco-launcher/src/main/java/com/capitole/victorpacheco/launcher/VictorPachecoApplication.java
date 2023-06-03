package com.capitole.victorpacheco.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.capitole.victorpacheco")
public class VictorPachecoApplication {
    public static void main(String[] args) {
        SpringApplication.run(VictorPachecoApplication.class, args);
    }
}

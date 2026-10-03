package com.example.HgsSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.example.HgsSystem") // Burada doğru paketi taradığınızdan emin olun
@ComponentScan(basePackages = "com.example.HgsSystem")  // Servislerinizin bulunduğu paketi ekliyoruz
public class HgsSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(HgsSystemApplication.class, args);
    }
}

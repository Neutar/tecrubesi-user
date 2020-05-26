package com.neutar.tecrubesi.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class TecrubesiUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(TecrubesiUserApplication.class, args);
    }
}

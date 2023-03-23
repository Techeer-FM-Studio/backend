package com.techeer.fmstudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FmStudioApplication {

    public static void main(String[] args) {
        SpringApplication.run(FmStudioApplication.class, args);
    }

}

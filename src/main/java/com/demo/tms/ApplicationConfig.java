package com.demo.tms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(JPAConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.demo.tms"})
public class ApplicationConfig {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationConfig.class, args);
    }
}

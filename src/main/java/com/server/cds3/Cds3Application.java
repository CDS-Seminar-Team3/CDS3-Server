package com.server.cds3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Cds3Application {

	public static void main(String[] args) {
		SpringApplication.run(Cds3Application.class, args);
	}

}

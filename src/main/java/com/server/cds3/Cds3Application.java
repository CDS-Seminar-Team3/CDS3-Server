package com.server.cds3;

import com.server.cds3.domain.Petition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;

@EnableJpaAuditing
@SpringBootApplication
public class Cds3Application {
	public static ArrayList<Petition> petitionList;
	public static void main(String[] args) {
		SpringApplication.run(Cds3Application.class, args);
		petitionList = new ArrayList<>();
	}

}

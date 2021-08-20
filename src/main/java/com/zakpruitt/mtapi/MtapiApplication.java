package com.zakpruitt.mtapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MtapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtapiApplication.class, args);
	}
}

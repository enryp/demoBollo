package com.aruba.demoBollo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.aruba.demoBollo.repository")
public class DemoBolloApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBolloApplication.class, args);
	}

}

package com.scalefocus.amdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.scalefocus.amdb")
@EnableJpaRepositories(basePackages = "com.scalefocus.amdb.repository")
public class AmdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmdbApplication.class, args);
	}

}

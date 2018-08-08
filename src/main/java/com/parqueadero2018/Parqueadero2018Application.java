package com.parqueadero2018;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"dao.repository, com.parqueadero2018"})
@EntityScan("dao.entity")
public class Parqueadero2018Application {

	public static void main(String[] args) {
		SpringApplication.run(Parqueadero2018Application.class, args);
	}
	
}
package com.parking2018;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"dao.repository, com.parking2018"})
@EntityScan("dao.entity")
public class Parking2018Application 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(Parking2018Application.class, args);
	}
	
}
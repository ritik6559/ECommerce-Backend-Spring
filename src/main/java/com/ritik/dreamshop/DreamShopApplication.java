package com.ritik.dreamshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DreamShopApplication {

	public static void main(String[] args) {
		System.out.println(args);
		SpringApplication.run(DreamShopApplication.class, args);
	}

}

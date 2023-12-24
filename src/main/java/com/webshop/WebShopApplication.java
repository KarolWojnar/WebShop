package com.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WebShopApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WebShopApplication.class, args);
	}

}

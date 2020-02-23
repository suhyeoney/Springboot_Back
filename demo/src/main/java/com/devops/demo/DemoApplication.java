package com.devops.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;


@EnableEncryptableProperties
@SpringBootApplication
public class DemoApplication {

	public static void main(String [] args) {
		SpringApplication application = new SpringApplication(DemoApplication.class);
		application.run(args);
		
	}
}

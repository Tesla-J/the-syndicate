package com.evilgemini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import com.evilgemini.entities.Admin;
//import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.evilgemini.controllers")
public class EvilGeminiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvilGeminiApplication.class, args);
		//var a = new Admin();
		//a.hello();
	}

}

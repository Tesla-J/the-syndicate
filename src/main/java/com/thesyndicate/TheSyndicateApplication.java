package com.thesyndicate;

import org.springframework.boot.SpringApplication;
//import com.example.KotlinFileKt;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TheSyndicateApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheSyndicateApplication.class, args);
		//KotlinFileKt.kotlinHello();
	}

	public static void javaHello() {
		System.out.println("Hello from java side");
	}

}

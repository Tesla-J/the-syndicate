package com.evilgemini;

import com.example.KotlinFileKt;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EvilGeminiApplication {

	public static void main(String[] args) {
		KotlinFileKt.kotlinHello();
	}

	public static void javaHello() {
		System.out.println("Hello from java side");
	}

}

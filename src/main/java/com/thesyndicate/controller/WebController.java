package com.thesyndicate.controller;

import java.util.Objects;

import com.thesyndicate.controller.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {
	@Autowired
	private UserController userController;

	@GetMapping(value = {"/", "index", "home"})
	public String index() {
		return "index";
	}
	
	//Login process
	
	@GetMapping(value = "/login")
	public String login(Model model) {
		return "login";
	}
	
	@PostMapping(value = "/login")
	public String login(String username, String password) {
		var user = userController.authenticate(username, password);
		
		if(Objects.isNull(user))
			System.out.println("Login failed");
		else
			System.out.println("Welcome");
		return "login";
	}
}

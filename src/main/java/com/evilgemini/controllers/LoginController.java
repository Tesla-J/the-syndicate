package com.evilgemini.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evilgemini.repositories.UserRepository;
import com.evilgemini.entity.User;

@Controller
@RequestMapping(value = "/func_login")
public class LoginController extends com.evilgemini.controllers.Controller<User, UserRepository>{
	@Autowired
	UserRepository repository;
	
	@PostMapping
	public String login(Model model) {
		System.out.println(model.getAttribute("username"));
		System.out.println(model.getAttribute("password"));
		return "func_login";
	}
}
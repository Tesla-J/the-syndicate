package com.evilgemini.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/evilgemini")
public class WebController {
	@GetMapping({"/", "/index"})
	public String index(@RequestParam(value = "namae", defaultValue = "NAMAE", required = false) String namae, Model model) {
		model.addAttribute("namae", namae);
		return "index";
	}
}

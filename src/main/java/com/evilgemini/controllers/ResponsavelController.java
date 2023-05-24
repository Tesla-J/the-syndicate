package com.evilgemini.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evilgemini.entities.Responsavel;
import com.evilgemini.repositories.ResponsavelRepository;

@RestController
@RequestMapping(value = "/evilgemini")
public class ResponsavelController {
	@Autowired
	ResponsavelRepository repository;
	
	@GetMapping(value = "/responsavel")
	private List<Responsavel> findAll(){
		return repository.findAll();
	}
	
	@PostMapping(value = "/responsavel")
	public Responsavel save(@RequestBody Responsavel responsavel) {
		return repository.save(responsavel);
	}
}

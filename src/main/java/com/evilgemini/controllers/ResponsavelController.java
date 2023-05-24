package com.evilgemini.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping(value = "/responsavel/{id}")
	private Responsavel findById(@PathVariable Integer id) {
		return repository.findById(id).get();
	}
	
	@PostMapping(value = "/responsavel")
	public Responsavel save(@RequestBody Responsavel responsavel) {
		return repository.save(responsavel);
	}
	
	@PutMapping (value = "/responsavel")
	public Responsavel update(@RequestBody Responsavel responsavel) {
		return repository.save(responsavel);
	}
	
	@DeleteMapping (value = "/responsavel")
	public Responsavel delete (@RequestBody Responsavel responsavel) {
		repository.delete(responsavel);
		return null;
	}
}

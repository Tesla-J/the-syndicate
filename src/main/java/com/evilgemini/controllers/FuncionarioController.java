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

import com.evilgemini.entities.Funcionario;
import com.evilgemini.entities.Responsavel;
import com.evilgemini.repositories.FuncionarioRepository;

@RestController
@RequestMapping (value = "/evilgemini")
public class FuncionarioController {
	
	@Autowired
	FuncionarioRepository repository;
	
	@GetMapping(value = "/funcionario")
	public List<Funcionario> findAll(){
		return repository.findAll();
	}
	
	@GetMapping(value = "/funcionario/{id}")
	private Funcionario findById(@PathVariable Integer id) {
		return repository.findById(id).get();
	}
	
	@PostMapping(value = "/funcionario")
	public Funcionario save(@RequestBody Funcionario funcionario) {
		return repository.save(funcionario);
	}
	
	@PutMapping (value = "/funcionario")
	public Funcionario update(@RequestBody Funcionario funcionario) {
		return repository.save(funcionario);
	}
	
	@DeleteMapping (value = "/funcionario")
	public Funcionario delete (@RequestBody Funcionario funcionario) {
		repository.delete(funcionario);
		return null;
	}
}

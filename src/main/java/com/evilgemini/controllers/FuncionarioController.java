package com.evilgemini.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evilgemini.entities.Funcionario;
import com.evilgemini.entities.Relatorio;
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
	
	@PostMapping(value = "/funcionario")
	public Funcionario save(@RequestBody Funcionario funcionario) {
		return repository.save(funcionario);
	}
}

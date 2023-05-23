package com.evilgemini.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evilgemini.entities.Funcionario;
import com.evilgemini.repositories.FuncionarioRepository;

@RestController
@RequestMapping (value = "/evilgemini")
public class FuncionarioController {
	
	@Autowired
	FuncionarioRepository repository;
	
	@GetMapping(value = "/funcionarios")
	public List<Funcionario> findAll(){
		return repository.findAll();
	}
}

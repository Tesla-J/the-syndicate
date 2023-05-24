package com.evilgemini.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evilgemini.entities.Relatorio;
import com.evilgemini.repositories.RelatorioRepository;

@RestController
@RequestMapping(value = "/evilgemini")
public class RelatorioController {

	@Autowired
	private RelatorioRepository repository;
	
	@GetMapping(value = "/relatorio")
	public List<Relatorio> findAll(){
		return repository.findAll();
	}
	
	@PostMapping(value = "/relatorio")
	public Relatorio save(@RequestBody Relatorio relatorio) {
		return repository.save(relatorio);
	}
}

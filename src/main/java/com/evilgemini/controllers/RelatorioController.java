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

import com.evilgemini.entities.Relatorio;
import com.evilgemini.entities.Responsavel;
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
	
	@GetMapping(value = "/relatorio/{id}")
	private Relatorio findById(@PathVariable Integer id) {
		return repository.findById(id).get();
	}
	
	@PostMapping(value = "/relatorio")
	public Relatorio save(@RequestBody Relatorio relatorio) {
		return repository.save(relatorio);
	}
	
	@PutMapping (value = "/relatorio")
	public Relatorio update(@RequestBody Relatorio relatorio) {
		return repository.save(relatorio);
	}
	
	@DeleteMapping (value = "/relatorio")
	public Responsavel delete (@RequestBody Relatorio relatorio) {
		repository.delete(relatorio);
		return null;
	}
}

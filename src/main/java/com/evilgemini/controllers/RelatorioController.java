package com.evilgemini.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evilgemini.entities.Relatorio;
import com.evilgemini.repositories.RelatorioRepository;

@RestController
@RequestMapping(value = "/evilgemini")
public class RelatorioController {

	@Autowired
	private RelatorioRepository repository;
	
	@GetMapping(value = "/relatorios")
	public List<Relatorio> findAll(){
		return repository.findAll();
	}
}

package com.evilgemini.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evilgemini.entities.Falecido;
import com.evilgemini.repositories.FalecidoRepository;

@RestController
@RequestMapping(value = "/evilgemini")
public class FalecidoController {
	
	@Autowired
	private FalecidoRepository repository;
	
	@GetMapping (value = "/falecidos")
	public List<Falecido> findAll(){
		return repository.findAll();
	}
}

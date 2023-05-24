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

import com.evilgemini.entities.Falecido;
import com.evilgemini.entities.Responsavel;
import com.evilgemini.repositories.FalecidoRepository;

@RestController
@RequestMapping(value = "/evilgemini")
public class FalecidoController {
	
	@Autowired
	private FalecidoRepository repository;
	
	@GetMapping (value = "/falecido")
	public List<Falecido> findAll(){
		return repository.findAll();
	}
	
	@GetMapping(value = "/falecido/{id}")
	private Falecido findById(@PathVariable Integer id) {
		return repository.findById(id).get();
	}
	
	@PostMapping(value = "/falecido")
	public Falecido save(@RequestBody Falecido falecido) {
		return repository.save(falecido);
	}
	
	@PutMapping (value = "/falecido")
	public Falecido update(@RequestBody Falecido falecido) {
		return repository.save(falecido);
	}
	
	@DeleteMapping (value = "/falecido")
	public Responsavel delete (@RequestBody Falecido falecido) {
		repository.delete(falecido);
		return null;
	}
}

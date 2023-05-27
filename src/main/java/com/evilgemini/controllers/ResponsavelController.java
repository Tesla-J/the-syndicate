package com.evilgemini.controllers;

import java.util.List;

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
public class ResponsavelController extends Controller<Responsavel, ResponsavelRepository>{
	private final String ENTITY_MAPPING = "/responsavel";
	private final String ENTITY_ID_MAPPING = ENTITY_MAPPING + "/{id}";
	
	@Override
	@GetMapping(value = ENTITY_MAPPING)
	protected List<Responsavel> findAll(){
		return super.findAll();
	}
	
	@Override
	@GetMapping(value = ENTITY_ID_MAPPING)
	protected Responsavel findById(@PathVariable Integer id) {
		return super.findById(id);
	}
	
	@Override
	@PostMapping(value = ENTITY_MAPPING)
	protected Responsavel save(@RequestBody Responsavel responsavel) {
		return super.save(responsavel);
	}
	
	@Override
	@PutMapping (value = ENTITY_MAPPING)
	protected Responsavel update(@RequestBody Responsavel responsavel) {
		return super.update(responsavel);
	}
	
	@Override
	@DeleteMapping (value = ENTITY_MAPPING)
	protected Responsavel delete (@RequestBody Responsavel responsavel) {
		return super.delete(responsavel);
	}
}

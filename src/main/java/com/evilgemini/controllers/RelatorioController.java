package com.evilgemini.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evilgemini.entities.Relatorio;
import com.evilgemini.repositories.RelatorioRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/evilgemini")
public class RelatorioController extends Controller<Relatorio, RelatorioRepository>{

	private final String ENTITY_MAPPING = "/relatorio";
	private final String ENTITY_ID_MAPPING = ENTITY_MAPPING + "/{id}";
	
	@Override
	@GetMapping(value = ENTITY_MAPPING)
	protected List<Relatorio> findAll(){
		return super.findAll();
	}
	
	@Override
	@GetMapping(value = ENTITY_ID_MAPPING)
	protected Relatorio findById(@PathVariable Integer id) {
		return super.findById(id);
	}
	
	@Override
	@PostMapping(value = ENTITY_MAPPING)
	protected Relatorio save(@RequestBody Relatorio relatorio) {
		return super.save(relatorio);
	}
	
	@Override
	@PutMapping (value = ENTITY_MAPPING)
	protected Relatorio update(@RequestBody Relatorio relatorio) {
		return super.update(relatorio);
	}
	
	@Override
	@DeleteMapping (value = ENTITY_MAPPING)
	protected Relatorio delete (@RequestBody Integer id) {
		return super.delete(id);
	}
}

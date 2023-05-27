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

import com.evilgemini.entities.Falecido;
import com.evilgemini.repositories.FalecidoRepository;

@RestController
@RequestMapping(value = "/evilgemini")
public class FalecidoController extends Controller<Falecido, FalecidoRepository>{
	
	private final String ENTITY_MAPPING = "/falecido";
	private final String ENTITY_ID_MAPPING = ENTITY_MAPPING + "/{id}";
	
	@Override
	@GetMapping(value = ENTITY_MAPPING)
	protected List<Falecido> findAll(){
		return super.findAll();
	}
	
	@Override
	@GetMapping(value = ENTITY_ID_MAPPING)
	protected Falecido findById(@PathVariable Integer id) {
		return super.findById(id);
	}
	
	@Override
	@PostMapping(value = ENTITY_MAPPING)
	protected Falecido save(@RequestBody Falecido falecido) {
		return super.save(falecido);
	}
	
	@Override
	@PutMapping (value = ENTITY_MAPPING)
	protected Falecido update(@RequestBody Falecido falecido) {
		return super.update(falecido);
	}
	
	@Override
	@DeleteMapping (value = ENTITY_MAPPING)
	protected Falecido delete (@RequestBody Falecido falecido) {
		return super.delete(falecido);
	}
}

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

import com.evilgemini.entities.Funcionario;
import com.evilgemini.repositories.FuncionarioRepository;

@RestController
@RequestMapping (value = "/evilgemini")
public class FuncionarioController extends Controller<Funcionario, FuncionarioRepository>{
	
	private final String ENTITY_MAPPING = "/funcionario";
	private final String ENTITY_ID_MAPPING = ENTITY_MAPPING + "/{id}";
	
	@Override
	@GetMapping(value = ENTITY_MAPPING)
	protected List<Funcionario> findAll(){
		return super.findAll();
	}
	
	@Override
	@GetMapping(value = ENTITY_ID_MAPPING)
	protected Funcionario findById(@PathVariable Integer id) {
		return super.findById(id);
	}
	
	@Override
	@PostMapping(value = ENTITY_MAPPING)
	protected Funcionario save(@RequestBody Funcionario funcionario) {
		return super.save(funcionario);
	}
	
	@Override
	@PutMapping (value = ENTITY_MAPPING)
	protected Funcionario update(@RequestBody Funcionario funcionario) {
		return super.update(funcionario);
	}
	
	@Override
	@DeleteMapping (value = ENTITY_MAPPING)
	protected Funcionario delete (@RequestBody Funcionario funcionario) {
		return super.delete(funcionario);
	}
}

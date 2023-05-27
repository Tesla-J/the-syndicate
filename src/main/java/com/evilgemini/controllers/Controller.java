package com.evilgemini.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;


public class Controller<E, R extends JpaRepository<E, Integer>>{
	
	@Autowired
	private R repository;
	
	protected List<E> findAll(){
		return repository.findAll();
	}
	
	protected E findById(Integer id) {
		return repository.findById(id).get();
	}

	protected E save(E entity) {
		return repository.save(entity);
	}
	
	protected E update(E entity) {
		return repository.save(entity);
	}
	
	protected E delete (E entity) {
		repository.delete(entity);
		return null;
	}
	
}
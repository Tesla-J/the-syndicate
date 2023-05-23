package com.evilgemini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evilgemini.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{

}

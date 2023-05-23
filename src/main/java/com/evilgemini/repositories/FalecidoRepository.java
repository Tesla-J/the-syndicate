package com.evilgemini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evilgemini.entities.Falecido;

public interface FalecidoRepository extends JpaRepository<Falecido, Integer> {

}

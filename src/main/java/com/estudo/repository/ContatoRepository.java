package com.estudo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudo.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
	
	public Optional<Contato> findById(Long id);

}

package com.estudo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.estudo.form.ContatoForm;
import com.estudo.model.Contato;
import com.estudo.repository.ContatoRepository;

@RestController
@RequestMapping("/contato")
public class ContatoController {

	@Autowired
	ContatoRepository contatoRepository;

	@GetMapping
	public ResponseEntity<List<Contato>> lista() {
		List<Contato> contatos = this.contatoRepository.findAll();
		return ResponseEntity.ok(contatos);

	}

	@PostMapping
	@Transactional
	public ResponseEntity<Contato> insere(@RequestBody @Valid ContatoForm form) {
		Contato contato = form.converter();
		contatoRepository.save(contato);
		return ResponseEntity.ok(contato);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Contato> detalhar(@PathVariable Long id) {
		Optional<Contato> contato = contatoRepository.findById(id);
		if (contato.isPresent()) {
			return ResponseEntity.ok(contato.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Contato> atualizar(@PathVariable Long id, @RequestBody @Valid ContatoForm form) {
		Optional<Contato> optional = contatoRepository.findById(id);
		if (optional.isPresent()) {
			Contato contato = contatoRepository.findById(id).get();
			contato.setEmail(form.getEmail());
			contato.setNome(form.getNome());
			contato.setTelefone(form.getTelefone());
			return ResponseEntity.ok(contato);
		}
		return ResponseEntity.notFound().build();
		
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Contato> optional = contatoRepository.findById(id);
		if (optional.isPresent()) {
			contatoRepository.deleteById(id);
			return ResponseEntity.ok().build();
			
		}
		return ResponseEntity.notFound().build();
	}

}

package com.estudo.form;

import javax.validation.constraints.NotNull;

import com.estudo.model.Contato;

public class ContatoForm {
	
	@NotNull
	private String nome;
	
	@NotNull
	private String email;
	
	@NotNull
	private String telefone;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Contato converter() {
		Contato contato = new Contato();
		contato.setNome(this.nome);
		contato.setEmail(this.email);
		contato.setTelefone(this.telefone);
		return contato;
	}

}

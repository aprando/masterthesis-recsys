package br.com.aprando.recommendersystem.controller;

import br.com.aprando.recommendersystem.domain.Avaliacao;

public class AvaliacaoForm {

	private String idUsuario;

	private Avaliacao[] avaliacoes;

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Avaliacao[] getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(Avaliacao[] avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	

}

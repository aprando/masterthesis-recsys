package br.com.aprando.recommendersystem.controller;

import br.com.aprando.recommendersystem.domain.Questao;

public class QuestionarioForm {

	private String idUsuario;

	private Questao[] questoes;

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Questao[] getQuestoes() {
		return questoes;
	}

	public void setQuestoes(Questao[] questoes) {
		this.questoes = questoes;
	}

}

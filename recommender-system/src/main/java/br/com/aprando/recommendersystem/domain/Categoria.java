package br.com.aprando.recommendersystem.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categoria")
public class Categoria {

	@Id
	private String id;

	private String fonte;

	private String idCategoriaPai;

	private String nomeCategoriaPai;

	private String nome;

	private List<Categoria> subCategorias;

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public String getNomeCategoriaPai() {
		return nomeCategoriaPai;
	}

	public void setNomeCategoriaPai(String nomeCategoriaPai) {
		this.nomeCategoriaPai = nomeCategoriaPai;
	}

	public String getIdCategoriaPai() {
		return idCategoriaPai;
	}

	public void setIdCategoriaPai(String idCategoriaPai) {
		this.idCategoriaPai = idCategoriaPai;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Categoria> getSubCategorias() {
		return subCategorias;
	}

	public void setSubCategorias(List<Categoria> subCategorias) {
		this.subCategorias = subCategorias;
	}

}

package br.com.aprando.recommendersystem.domain;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "produto")
public class Produto {

	@Id
	private String id;

	private Long sku;

	private String nome;

	private String fonte;

	private String descricaoLonga;

	private String descricaoLongaHtml;

	private String descricaoCurta;

	private String descricaoCurtaHtml;

	private String imagem;

	private String imagemGrande;

	private Double preco;

	private List<Categoria> categorias;

	private List<String> caracteristicas;

	private HashMap<String, String> detalhes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getSku() {
		return sku;
	}

	public void setSku(Long sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public String getDescricaoLonga() {
		return descricaoLonga;
	}

	public void setDescricaoLonga(String descricaoLonga) {
		this.descricaoLonga = descricaoLonga;
	}

	public String getDescricaoLongaHtml() {
		return descricaoLongaHtml;
	}

	public void setDescricaoLongaHtml(String descricaoLongaHtml) {
		this.descricaoLongaHtml = descricaoLongaHtml;
	}

	public String getDescricaoCurta() {
		return descricaoCurta;
	}

	public void setDescricaoCurta(String descricaoCurta) {
		this.descricaoCurta = descricaoCurta;
	}

	public String getDescricaoCurtaHtml() {
		return descricaoCurtaHtml;
	}

	public void setDescricaoCurtaHtml(String descricaoCurtaHtml) {
		this.descricaoCurtaHtml = descricaoCurtaHtml;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getImagemGrande() {
		return imagemGrande;
	}

	public void setImagemGrande(String imagemGrande) {
		this.imagemGrande = imagemGrande;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<String> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<String> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public HashMap<String, String> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(HashMap<String, String> detalhes) {
		this.detalhes = detalhes;
	}

}

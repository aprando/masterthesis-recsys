package br.com.aprando.recommendersystem.domain;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

//@Entity(name = "usuario")
@Document(collection = "usuario")
public class Usuario {

	// @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private String id;

	// @Column
	private String nome;

	// @Column
	private String sexo;

	// @Column
	private String ocupacao;

	// @Column
	private String email;

	// @Column(name = "data_nascimento")
	private Date dataNascimento;

	// @Column(name = "facebook_id")
	private String facebookId;

	// @Column(name = "twitter_id")
	private String twitterId;

	// @Column(name = "instagram_id")
	private String instagramId;

	// @Column(name = "googleplus_id")
	private String googleplusId;

	// @Column(name = "facebook_accesstoken")
	private String facebookAcessToken;

	private String status = "Ativo";

	private Questao[] questionario;

	public Questao[] getQuestionario() {
		return questionario;
	}

	public void setQuestionario(Questao[] questionario) {
		this.questionario = questionario;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Transient
	private UsuarioFacebook usuarioFacebook;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UsuarioFacebook getUsuarioFacebook() {
		return usuarioFacebook;
	}

	public void setUsuarioFacebook(UsuarioFacebook usuarioFacebook) {
		this.usuarioFacebook = usuarioFacebook;
	}

	public String getFacebookAcessToken() {
		return facebookAcessToken;
	}

	public void setFacebookAcessToken(String facebookAcessToken) {
		this.facebookAcessToken = facebookAcessToken;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public String getInstagramId() {
		return instagramId;
	}

	public void setInstagramId(String instagramId) {
		this.instagramId = instagramId;
	}

	public String getGoogleplusId() {
		return googleplusId;
	}

	public void setGoogleplusId(String googleplusId) {
		this.googleplusId = googleplusId;
	}

}

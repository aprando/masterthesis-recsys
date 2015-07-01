package br.com.aprando.recommendersystem.base;

import java.util.UUID;

public class BaseException extends Exception {

	private static final long serialVersionUID = -6386792487252812136L;
	
	private String uuid;
	private String erro;
	
	public BaseException() {}

	public BaseException(Exception e) {
		super(e);
	}
	
	public BaseException(final String erro) {
		super(erro);
		UUID id = UUID.randomUUID();
		this.uuid = id.toString();
		this.erro = erro;
	}
	
	public String getErro() {
		return erro;
	}
	
	public void setErro(String erro) {
		this.erro = erro;
	}
	
	public String getUuid() {
		return uuid;
	}
	
}

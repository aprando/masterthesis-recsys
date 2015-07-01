package br.com.aprando.recommendersystem.base;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1154023122154552926L;

	public ServiceException(Exception e){
		super(e);
	}
	
}

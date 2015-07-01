package br.com.aprando.recommendersystem.service;

import br.com.aprando.recommendersystem.base.ServiceException;
import br.com.aprando.recommendersystem.domain.UsuarioFacebook;

import com.restfb.Connection;
import com.restfb.types.Insight;

public interface SocialMidiaService {

	String consultarFacebookUserJson(String facebookId) throws ServiceException;
	
	String consultarFacebookLongLifeAccessToken(String shortLifeAccessToken) throws ServiceException;
	
	String consultarFacebookUserJson(String facebookId, String accessToken) throws ServiceException;
	
	UsuarioFacebook consultarUsuarioFacebook(String facebookId) throws ServiceException;

	UsuarioFacebook consultarUsuarioFacebook(String facebookId, String accessToken) throws ServiceException;
	
	Connection<Insight> consultarFacebookInsights() throws ServiceException;
	
	String salvarDadosUsuarioFacebook(String facebookId) throws ServiceException;

}

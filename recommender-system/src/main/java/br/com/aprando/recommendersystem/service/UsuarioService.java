package br.com.aprando.recommendersystem.service;

import java.util.List;

import br.com.aprando.recommendersystem.base.ServiceException;
import br.com.aprando.recommendersystem.domain.Questao;
import br.com.aprando.recommendersystem.domain.Usuario;

public interface UsuarioService {

	List<Usuario> listarTodos() throws ServiceException;
	
	Usuario consultarPorID(String id) throws ServiceException;
	
	Usuario consultarPorFacebookId(String facebookId) throws ServiceException;

	Usuario consultarPorTwitterId(String twitterId) throws ServiceException;

	Usuario consultarPorNome(String nome) throws ServiceException;

	Usuario salvar(Usuario usuario) throws ServiceException;
	
	Usuario salvarRespostas(String idUsuario, Questao[] respostas) throws ServiceException;
	
	void remover(String id) throws ServiceException;
	
}

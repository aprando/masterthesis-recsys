package br.com.aprando.recommendersystem.service;

import java.util.List;

import br.com.aprando.recommendersystem.base.ServiceException;
import br.com.aprando.recommendersystem.domain.Avaliacao;


public interface AvaliacaoService {

	Avaliacao salvar(Avaliacao avaliacao) throws ServiceException;

	Avaliacao consultarPorID(String id) throws ServiceException;
	
	Avaliacao consultarPorIdUsuarioIdProduto(String idUsuario, String idProduto) throws ServiceException;

	List<Avaliacao> listarTodos() throws ServiceException;

}

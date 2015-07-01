package br.com.aprando.recommendersystem.service;

import br.com.aprando.recommendersystem.base.ServiceException;

public interface BestBuyService {

	void cargaCategorias() throws ServiceException;

	void cargaProdutos() throws ServiceException;

}

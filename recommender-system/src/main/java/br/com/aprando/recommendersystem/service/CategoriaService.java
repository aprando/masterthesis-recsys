package br.com.aprando.recommendersystem.service;

import java.util.List;

import br.com.aprando.recommendersystem.base.ServiceException;
import br.com.aprando.recommendersystem.domain.Categoria;


public interface CategoriaService {

	List<Categoria> listarTodos() throws ServiceException;
	
	List<Categoria> listarIdCategoriaSemSubcategoria() throws ServiceException;
	
	Categoria consultarPorID(String id) throws ServiceException;
	
	Categoria salvar(Categoria categoria) throws ServiceException;
	
	void remover(String id) throws ServiceException;

}

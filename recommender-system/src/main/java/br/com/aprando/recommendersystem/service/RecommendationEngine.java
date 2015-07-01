package br.com.aprando.recommendersystem.service;

import java.util.List;

import br.com.aprando.recommendersystem.base.ServiceException;
import br.com.aprando.recommendersystem.domain.Produto;


public interface RecommendationEngine {

	public List<Produto> knn(Long usuarioId) throws ServiceException;
	
	public List<Produto> svm(Long usuarioId) throws ServiceException;
	
	public List<Produto> naiveBayes(Long usuarioId) throws ServiceException;
	
}

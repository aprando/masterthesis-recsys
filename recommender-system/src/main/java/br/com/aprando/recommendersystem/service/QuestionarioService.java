package br.com.aprando.recommendersystem.service;

import java.util.List;

import br.com.aprando.recommendersystem.base.ServiceException;
import br.com.aprando.recommendersystem.domain.Questao;


public interface QuestionarioService {

	List<Questao> listarTodos() throws ServiceException;
	
	List<Questao> listarTodosPorIdioma(String idioma) throws ServiceException;
	
}

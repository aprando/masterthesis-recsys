package br.com.aprando.recommendersystem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weka.core.Instances;
import br.com.aprando.recommendersystem.base.ServiceException;
import br.com.aprando.recommendersystem.domain.Produto;


@Transactional
@Service
public class RecommendationEngineImpl implements RecommendationEngine {

	@Override
	public List<Produto> knn(Long usuarioId) throws ServiceException {
		//utilizar weka
		return null;
	}

	@Override
	public List<Produto> svm(Long usuarioId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produto> naiveBayes(Long usuarioId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}

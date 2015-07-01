package br.com.aprando.recommendersystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import br.com.aprando.recommendersystem.base.ServiceException;
import br.com.aprando.recommendersystem.domain.Avaliacao;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

	@Autowired
	MongoOperations mongoTemplate;

	@Override
	public List<Avaliacao> listarTodos() throws ServiceException {
		return mongoTemplate.findAll(Avaliacao.class);
	}

	@Override
	public Avaliacao consultarPorID(String id) throws ServiceException {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));

		return mongoTemplate.findOne(query, Avaliacao.class);
	}

	@Override
	public Avaliacao salvar(Avaliacao avaliacao) throws ServiceException {
		mongoTemplate.save(avaliacao);
		return avaliacao;
	}

	@Override
	public Avaliacao consultarPorIdUsuarioIdProduto(String idUsuario, String idProduto) throws ServiceException {
		Query query = new Query();
		query.addCriteria(Criteria.where("idUsuario").is(idUsuario));
		query.addCriteria(Criteria.where("idProduto").is(idProduto));

		return mongoTemplate.findOne(query, Avaliacao.class);
	}

}

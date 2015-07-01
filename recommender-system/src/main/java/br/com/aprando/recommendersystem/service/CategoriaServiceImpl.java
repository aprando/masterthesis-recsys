package br.com.aprando.recommendersystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import br.com.aprando.recommendersystem.base.ServiceException;
import br.com.aprando.recommendersystem.domain.Categoria;

@Service
public class CategoriaServiceImpl implements CategoriaService {


	@Autowired
	MongoOperations mongoTemplate;

	@Override
	public List<Categoria> listarTodos() throws ServiceException {
		return mongoTemplate.findAll(Categoria.class);
	}

	@Override
	public Categoria consultarPorID(String id) throws ServiceException {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));

		return mongoTemplate.findOne(query, Categoria.class);
	}

	@Override
	public Categoria salvar(Categoria categoria) throws ServiceException {
		mongoTemplate.save(categoria);
		return categoria;
	}

	@Override
	public void remover(String id) throws ServiceException {
		mongoTemplate.remove(Criteria.where("_id").is(id));
	}

	@Override
	public List<Categoria> listarIdCategoriaSemSubcategoria()
			throws ServiceException {
		BasicQuery query = new BasicQuery("{ subCategorias: { $size: 0 } }");
		query.fields().include("_id");
		return mongoTemplate.find(query, Categoria.class);
	}

}

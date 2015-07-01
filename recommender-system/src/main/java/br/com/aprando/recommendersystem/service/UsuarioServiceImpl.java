package br.com.aprando.recommendersystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import br.com.aprando.recommendersystem.base.ServiceException;
import br.com.aprando.recommendersystem.domain.Questao;
import br.com.aprando.recommendersystem.domain.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	MongoOperations mongoTemplate;

	@Override
	public List<Usuario> listarTodos() throws ServiceException {
		return mongoTemplate.findAll(Usuario.class);
	}

	@Override
	public Usuario consultarPorID(String id) throws ServiceException {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));

		return mongoTemplate.findOne(query, Usuario.class);
	}

	@Override
	public Usuario consultarPorFacebookId(String facebookId)
			throws ServiceException {
		Query query = new Query();
		query.addCriteria(Criteria.where("facebookId").is(facebookId));

		return mongoTemplate.findOne(query, Usuario.class);
	}

	@Override
	public Usuario salvar(Usuario usuario) throws ServiceException {
		//Garantindo que nao sera salvo usuario com id=""
		if("".equals(usuario.getId()))
			usuario.setId(null);

		mongoTemplate.save(usuario);
		return usuario;
	}

	@Override
	public void remover(String id) throws ServiceException {
		mongoTemplate.remove(Criteria.where("id").is(id));

	}

	@Override
	public Usuario salvarRespostas(String idUsuario, Questao[] respostas) throws ServiceException {
		Usuario usuario = consultarPorID(idUsuario);
		if(usuario == null){
			usuario = new Usuario();
			usuario.setId(idUsuario);
		}
		usuario.setQuestionario(respostas);
		usuario = salvar(usuario);	
		return usuario;
	}

	@Override
	public Usuario consultarPorTwitterId(String twitterId) throws ServiceException {
		Query query = new Query();
		query.addCriteria(Criteria.where("twitterId").is(twitterId));

		return mongoTemplate.findOne(query, Usuario.class);
	}

	@Override
	public Usuario consultarPorNome(String nome) throws ServiceException {
		Query query = new Query();
		query.addCriteria(Criteria.where("nome").is(nome));

		return mongoTemplate.findOne(query, Usuario.class);
	}
	
	
	/*
	 * @PersistenceContext(name = "recsys-pu") EntityManager entityManager;
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<Usuario> listarTodos() throws ServiceException {
	 * Query q = entityManager.createQuery("select usu from usuario usu ");
	 * return q.getResultList(); }
	 * 
	 * @Override public Usuario consultarPorPK(Integer id) throws
	 * ServiceException { Query q =
	 * entityManager.createQuery("select u from usuario u where u.id = " + id);
	 * return (Usuario) q.getSingleResult(); }
	 * 
	 * @Override public Usuario salvar(Usuario usuario) throws ServiceException
	 * { if (usuario.getId() == null || usuario.getId().intValue() == 0)
	 * entityManager.persist(usuario); else entityManager.merge(usuario); return
	 * usuario; }
	 * 
	 * @Override public void remover(Integer id) throws ServiceException { Query
	 * q =
	 * entityManager.createQuery("select usu from usuario usu where usu.id = " +
	 * id); Usuario u = (Usuario) q.getSingleResult(); entityManager.remove(u);
	 * }
	 * 
	 * @Override public Usuario consultarPorFacebookId(String facebookId) throws
	 * ServiceException { try{ Query q = entityManager.createQuery(
	 * "select usu from usuario usu where usu.facebookId = " + facebookId);
	 * return (Usuario) q.getSingleResult(); }catch(NoResultException nr){
	 * return null; }catch(Exception e){ throw new ServiceException(e); } }
	 */

}

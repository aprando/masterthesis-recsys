package br.com.aprando.recommendersystem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.aprando.recommendersystem.domain.Produto;
import br.com.aprando.recommendersystem.domain.Usuario;
import br.com.aprando.recommendersystem.service.ProdutoService;
import br.com.aprando.recommendersystem.service.SocialMidiaService;
import br.com.aprando.recommendersystem.service.UsuarioService;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	SocialMidiaService socialMidiaService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listar(HttpServletRequest request) {
		try {
			request.setAttribute("usuarios", usuarioService.listarTodos());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", ExceptionUtils.getStackTrace(e));
			return "erro";
		}

		return "admin/usuarios/lista";
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public String novo(HttpServletRequest request) {
		request.setAttribute("usuario", new Usuario());
		return "admin/usuarios/detalhe";
	}

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(Usuario formBean, HttpServletRequest request) {
		try {
			Usuario p = usuarioService.consultarPorID(formBean.getId());
			request.setAttribute("cat", p);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", ExceptionUtils.getStackTrace(e));
			return "erro";
		}
		return "admin/usuarios/formulario";
	}

	@RequestMapping(value = "/detalhar-dados-sociais", method = RequestMethod.GET)
	public String detalharDadosSociais(Usuario formBean, HttpServletRequest request) {
		try {
			Usuario u = usuarioService.consultarPorID(formBean.getId());
			if(u != null && u.getFacebookId() != null){
				request.setAttribute("usuarioFacebook", socialMidiaService.consultarUsuarioFacebook(u.getFacebookId(), u.getFacebookAcessToken()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", ExceptionUtils.getStackTrace(e));
			return "erro";
		}
		return "admin/usuarios/dados-sociais";
	}
	
	@RequestMapping(value = "/detalhar-dados-facebook", method = RequestMethod.GET)
	@ResponseBody public String detalharDadosFacebook(Usuario formBean, HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("application/json");
			
			Usuario u = usuarioService.consultarPorID(formBean.getId());
			return socialMidiaService.consultarFacebookUserJson(u.getFacebookId());
		} catch (Exception e) {
			e.printStackTrace();
			return ExceptionUtils.getStackTrace(e);
		}
	}	
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvar(Usuario formBean, RedirectAttributes redirectAttrs) {
		try {
			usuarioService.salvar(formBean);
			redirectAttrs.addFlashAttribute("message", "Usuario id " + formBean.getId() + " salvo com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("erro", ExceptionUtils.getStackTrace(e));
			return "erro";
		}
		return "redirect:/admin/usuarios/";
	}
	
	@RequestMapping(value = "/salvar-usuario-rede-social", method = RequestMethod.POST)
	@ResponseBody public Usuario salvarRedeSocial(Usuario formBean, RedirectAttributes redirectAttrs) {
		Usuario usuario = null;
		try{
			//Primeira busco pelo id, para nao duplicar o usuario.
			if(!"".equals(formBean.getId()))
				usuario = usuarioService.consultarPorID(formBean.getId());
			
			//Primeiro, verifica se o facebookId foi informado.
			//Se sim, recupera o token de acesso para prologar o prazo da app
			if(!"".equals(formBean.getFacebookId())){

				//Se nao conseguiu recuperar pelo ID, verifica se existe um usuario com
				// esse facebookId. Se existir, recupera. Se nao existir e o suaurio for nulo,
				// cria um novo usuario.
				if(usuario == null){
					usuario = usuarioService.consultarPorFacebookId(formBean.getFacebookId());
					if(usuario == null)
						usuario = new Usuario();
				}
				usuario.setFacebookId(formBean.getFacebookId());
				
				//Valida se o facebookId do usuario com o ID informado pela view 
				// eh o mesmo do registrado no mongo. Se for divergente, duplicar
				// o usuario e verificar problema de logica.
				if(!"".equals(usuario.getFacebookId()) && !formBean.getFacebookId().equals(usuario.getFacebookId())){
					usuario.setStatus("FacebookId divergente! Duplicando id " + usuario.getId());
					usuario.setId(null);
				}
				
				String accessToken = formBean.getFacebookAcessToken();
				if(accessToken != null && !accessToken.equals(""))
					accessToken = socialMidiaService.consultarFacebookLongLifeAccessToken(formBean.getFacebookAcessToken());
				
				usuario.setFacebookAcessToken(accessToken);
			}
			
			if(!"".equals(formBean.getTwitterId())){
				
				//Se nao conseguiu recuperar pelo ID, verifica se existe um usuario com
				// esse TwitterId. Se existir, recupera. Se nao existir e o suaurio for nulo,
				// cria um novo usuario.
				if(usuario == null){
					usuario = usuarioService.consultarPorTwitterId(formBean.getTwitterId());
					if(usuario == null)
						usuario = new Usuario();
				}
				usuario.setTwitterId(formBean.getTwitterId());
				
				//Valida se o twitterId do usuario com o ID informado pela view 
				// eh o mesmo do registrado no mongo. Se for divergente, duplicar
				// o usuario e verificar problema de logica.
				if(!"".equals(usuario.getTwitterId()) && !formBean.getTwitterId().equals(usuario.getTwitterId())){
					usuario.setStatus("TwitterId divergente! Duplicando id " + usuario.getId());
					usuario.setId(null);
				}
				
			}
			
			if(usuario == null)
				throw new Exception("Favor informar ao menos uma rede social");
			
			usuario.setSexo(formBean.getSexo());
			usuario.setNome(formBean.getNome());
			usuario.setEmail(formBean.getEmail());
			
			usuario = usuarioService.salvar(usuario);	
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return usuario;
	}
		
	@RequestMapping(value = "/recuperar-recomendacoes", method = RequestMethod.POST)
	@ResponseBody public Produto[] recomendar(Usuario formBean, RedirectAttributes redirectAttrs) {
		List<Produto> produtos = new ArrayList<Produto>();
		try{
			produtos.addAll(produtoService.listarTodos(1));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return produtos.toArray(new Produto[produtos.size()]);
	}	
	
	@RequestMapping(value = "/remover", method = RequestMethod.GET)
	public String remover(Usuario formBean, RedirectAttributes redirectAttrs) {
		try {
			usuarioService.remover(formBean.getId());
			redirectAttrs.addFlashAttribute("message", "Usuario id "+ formBean.getId() + " removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("erro", ExceptionUtils.getStackTrace(e));
			return "erro";
		}
		return "redirect:/admin/usuarios/";
	}

}
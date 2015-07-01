package br.com.aprando.recommendersystem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.aprando.recommendersystem.base.ServiceException;
import br.com.aprando.recommendersystem.domain.Produto;
import br.com.aprando.recommendersystem.service.BestBuyService;
import br.com.aprando.recommendersystem.service.ProdutoService;

@Controller
@RequestMapping("/admin/produtos")
public class ProdutoController {

	@Autowired
	BestBuyService bestBuyService;

	@Autowired
	ProdutoService produtoService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		return "admin/produtos/produtos";
	}
	
	@RequestMapping(value = "/importar-best-buy", method = RequestMethod.GET)
	public String importarBestBuy(HttpServletRequest request) {
		try {
			bestBuyService.cargaProdutos();
			request.setAttribute("produtos", produtoService.listarTodos());
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute("erro", ExceptionUtils.getStackTrace(e));
			return "erro";
		}
		return "admin/produtos/lista";
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(HttpServletRequest request) {
		try {
			String pagina = request.getParameter("pagina");
			if(pagina == null || pagina.equals(""))
				pagina = "1";
			
			List<Produto> produtos = produtoService.listarTodos(Integer.parseInt(pagina));
			
			request.setAttribute("paginas", produtoService.getTotalPaginas());
			request.setAttribute("pagina", Integer.parseInt(pagina));
			request.setAttribute("produtos", produtos);
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute("erro", ExceptionUtils.getStackTrace(e));
			return "erro";
		}
		return "admin/produtos/lista";
	}
	
	@RequestMapping(value = "/detalhar", method = RequestMethod.GET)
	public String detalhar(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			request.setAttribute("produto", produtoService.consultarPorID(id));
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute("erro", ExceptionUtils.getStackTrace(e));
			return "erro";
		}
		return "admin/produtos/detalhe";
	}	

	@RequestMapping(value = "/kmeans-grupo", method = RequestMethod.GET)
	public String kmeans(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			request.setAttribute("produtos", produtoService.listarPorIdProdutoAgrupadoPorKmeansClusterGroup(id));
			//FIXME: Paginar!!!
			request.setAttribute("paginas", 1);
			request.setAttribute("pagina", 1);
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute("erro", ExceptionUtils.getStackTrace(e));
			return "erro";
		}
		return "admin/produtos/kmeans-grupo";
	}	

}
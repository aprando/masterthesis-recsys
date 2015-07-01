package br.com.aprando.recommendersystem.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.aprando.recommendersystem.base.ServiceException;
import br.com.aprando.recommendersystem.service.BestBuyService;
import br.com.aprando.recommendersystem.service.CategoriaService;

@Controller
@RequestMapping("/admin/categorias")
public class CategoriaController {

	@Autowired
	BestBuyService bestBuyService;

	@Autowired
	CategoriaService categoriaService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		return "admin/categorias/categorias";
	}

	@RequestMapping(value = "/importar-best-buy", method = RequestMethod.GET)
	public String importarBestBuy(HttpServletRequest request) {
		try {
			bestBuyService.cargaCategorias();
			request.setAttribute("categorias", categoriaService.listarTodos());
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute("erro", ExceptionUtils.getStackTrace(e));
			return "erro";
		}
		return "admin/categorias/lista";
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(HttpServletRequest request) {
		try {
			request.setAttribute("categorias", categoriaService.listarTodos());
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute("erro", ExceptionUtils.getStackTrace(e));
			return "erro";
		}
		return "admin/categorias/lista";
	}
	
}
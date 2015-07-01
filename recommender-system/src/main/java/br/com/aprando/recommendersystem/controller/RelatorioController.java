package br.com.aprando.recommendersystem.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.aprando.recommendersystem.service.UsuarioService;

@Controller
@RequestMapping("/admin/relatorios/")
public class RelatorioController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listar(HttpServletRequest request) {
		return "admin/relatorios/formulario";
	}

}
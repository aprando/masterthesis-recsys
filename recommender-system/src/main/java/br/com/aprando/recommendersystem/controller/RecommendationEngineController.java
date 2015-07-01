package br.com.aprando.recommendersystem.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/recommendation-engine/")
public class RecommendationEngineController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listar(HttpServletRequest request) {
		return "admin/recommendationengine/formulario";
	}

}
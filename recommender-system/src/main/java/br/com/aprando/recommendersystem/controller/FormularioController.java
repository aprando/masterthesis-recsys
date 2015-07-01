package br.com.aprando.recommendersystem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.aprando.recommendersystem.domain.Avaliacao;
import br.com.aprando.recommendersystem.domain.Questao;
import br.com.aprando.recommendersystem.service.AvaliacaoService;
import br.com.aprando.recommendersystem.service.QuestionarioService;
import br.com.aprando.recommendersystem.service.UsuarioService;

@Controller
@RequestMapping("/formulario")
public class FormularioController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	QuestionarioService questionarioService;

	@Autowired
	AvaliacaoService avaliacaoService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listar(HttpServletRequest request) {
		List<Questao> questoes = new ArrayList<Questao>();
		try {
			questoes.addAll(questionarioService.listarTodos());
			request.setAttribute("questionario", questoes);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", ExceptionUtils.getStackTrace(e));
			return "erro";
		}
		return "formulario-dissertacao";
	}

	@RequestMapping(value = "/salvar-questionario", method = RequestMethod.POST)
	@ResponseBody
	public String salvarQuestionario(QuestionarioForm formBean) {
		try {
			for (Questao q : formBean.getQuestoes())
				if (q == null || q.getResposta() == null
						|| "".equals(q.getResposta()))
					return "false";

			usuarioService.salvarRespostas(formBean.getIdUsuario(),
					formBean.getQuestoes());
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
		return "true";
	}

	@RequestMapping(value = "/salvar-avaliacoes", method = RequestMethod.POST)
	@ResponseBody
	public String salvarAvaliacoes(AvaliacaoForm formBean) {
		try {
			Avaliacao avaliacaoExistente = null;
			
			for (Avaliacao a : formBean.getAvaliacoes()) {
				if (a == null || a.getAvaliacao() == null || "".equals(a.getAvaliacao()))
					continue;
				
				avaliacaoExistente = avaliacaoService.consultarPorIdUsuarioIdProduto(formBean.getIdUsuario(), a.getIdProduto());
				if(avaliacaoExistente != null)
					a.setId(avaliacaoExistente.getId());
				a.setIdUsuario(formBean.getIdUsuario());
				avaliacaoService.salvar(a);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
		return "true";
	}

}
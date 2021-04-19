package mytest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mytest.models.Contrato;
import mytest.service.PagamentoService;

@Controller
public class HomeController {
	
	@Autowired
	private ContratoService service;

	@RequestMapping("/")
	public String index(Model model) {
		Filtro filtro = new Filtro("cnpj_contratada");
		model.addAttribute("filtro", filtro);
		return "index";
	}
	
	@RequestMapping(value = "buscadados", method = RequestMethod.POST)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public String buscaDados(HttpServletRequest request, HttpServletResponse response, @RequestParam("filtro") String filtro, @RequestParam("uasg") String uasg, RedirectAttributes redirect, Model model) throws IOException, ServletException {
		
		response.setContentType("text/csv;charset=UTF-8");
		
		if(null == filtro || null == uasg || "".equals(filtro) || "".equals(uasg)) {
			redirect.addFlashAttribute("message", "Verifique os parâmetros de entrada e tente novamente por favor!");
			redirect.addFlashAttribute("alertClass", "alert-danger");
			return "redirect:/";
		} 
		try {	
			model.addAttribute("filtro", new Filtro(filtro));
			model.addAttribute("message", "Arquivo baixado com sucesso!");
			model.addAttribute("alertClass", "alert-success");

			response.addHeader("Content-Disposition", "attachment; filename = Contratos_" + uasg  + "AgrupadoPor_" + filtro + ".csv");
			response.setStatus(200);
			response.getWriter().write(service.buscarDadosGov(filtro, uasg));
			
			return "index";
		} catch(Exception e) {
			redirect.addFlashAttribute("message", "API Gov indisponível no momento. Favor tente novamente mais tarde!");
			redirect.addFlashAttribute("alertClass", "alert-danger");
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "adicionacontrato", method = RequestMethod.POST)
	public String adicionaDados(@RequestParam("identificador") String identificador, Model model) {
		
		Contrato contrato = new Contrato(identificador);
		service.salvar(contrato);
		
		Iterable<Contrato> contratos = service.obterTodos();
		model.addAttribute("contratos", contratos);
		
		return "listacontrato";
	}

	@RequestMapping("removecontrato/{identificador}")
	public String removeCompra(@PathVariable("identificador") String identificador, Model model) {
		
		service.remover(identificador);
		Iterable<Contrato> contratos = service.obterTodos();
		model.addAttribute("contratos", contratos);
		
		return "listacontrato";
	}
	

}

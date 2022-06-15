package br.com.alura.mvc.mudi.controller;


import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	 //principal pega os dados do usuario logado
	
	@GetMapping             
	public String Home(Model model, Principal principal) {
		
		Sort sort = Sort.by("dataEntrega").descending();
		
		PageRequest paginacao = PageRequest.of(0, 2, sort);
		
		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.ENTREGUE, paginacao);
		model.addAttribute("pedido", pedidos);
		return "home";
	}
	
	//pega a url e busca pelo o status transformando enum em string
	

	//Se colocar url errada vai para home
	
	//@ExceptionHandler(IllegalArgumentException.class)
	//public String onError() {
	//	return "redirect:/home";
	//}
	
}

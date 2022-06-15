package br.com.alura.mvc.mudi.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.intercepetor.InterceptadorDeAcessos;
import br.com.alura.mvc.mudi.intercepetor.InterceptadorDeAcessos.Acesso;

@RequestMapping("/api/acessos")
@RestController
public class AcessosRest {

	@GetMapping
	public List<Acesso> getAcessos(){
		return InterceptadorDeAcessos.acessos;
	}
}
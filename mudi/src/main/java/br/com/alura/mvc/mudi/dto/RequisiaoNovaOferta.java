package br.com.alura.mvc.mudi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.alura.mvc.mudi.model.Oferta;

public class RequisiaoNovaOferta {
	
	private static final DateTimeFormatter formmater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	private int pedidoId;
	
	@Pattern(regexp = "^\\d+(\\.\\d+{2})?$")
	@NotBlank
	private String valor;
	
	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
	@NotNull
	private String dataEntrega;
	private String comentario;
	
	
	public int getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(int pedidoId) {
		this.pedidoId = pedidoId;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Oferta toOferta() {
		Oferta oferta = new Oferta();
		oferta.setComentario(this.comentario);
		oferta.setDataEntrega(LocalDate.parse(this.dataEntrega, formmater));
		oferta.setValor(new BigDecimal(this.valor));
		
		return oferta;
	}
	

}

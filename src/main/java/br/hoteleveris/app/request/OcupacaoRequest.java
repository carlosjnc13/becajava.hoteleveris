package br.hoteleveris.app.request;

import org.springframework.beans.factory.annotation.Value;

import br.hoteleveris.app.model.Cliente;
import br.hoteleveris.app.model.Quarto;

public class OcupacaoRequest {
	
	private String data;
	private Long qtdDiarias;
	
	@Value("${situacao:'N'}")
	private String situacao;
	private Cliente cliente;
	private Quarto quarto;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Long getQtdDiarias() {
		return qtdDiarias;
	}
	public void setQtdDiarias(Long qtdDiarias) {
		this.qtdDiarias = qtdDiarias;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Quarto getQuarto() {
		return quarto;
	}
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
	
	
	

}

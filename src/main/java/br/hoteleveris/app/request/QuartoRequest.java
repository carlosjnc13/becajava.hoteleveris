package br.hoteleveris.app.request;

import java.util.List;

public class QuartoRequest {

	private int andar;
	private int noQuarto;
	private String situacao;
	private Long IdtipoQuarto;
	
	private List<ComodidadeRequest> comodidades;
	
	public List<ComodidadeRequest> getComodidades() {
		return comodidades;
	}
	public void setComodidades(List<ComodidadeRequest> comodidades) {
		this.comodidades = comodidades;
	}
	public int getAndar() {
		return andar;
	}
	public void setAndar(int andar) {
		this.andar = andar;
	}
	public int getNoQuarto() {
		return noQuarto;
	}
	public void setNoQuarto(int noQuarto) {
		this.noQuarto = noQuarto;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public Long getIdtipoQuarto() {
		return IdtipoQuarto;
	}
	public void setIdtipoQuarto(Long idtipoQuarto) {
		IdtipoQuarto = idtipoQuarto;
	}
	
	
	
	
	
}

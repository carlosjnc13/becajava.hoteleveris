package br.hoteleveris.app.request;

import java.util.Set;

import br.hoteleveris.app.model.Comodidade;
import br.hoteleveris.app.model.TipoQuarto;

public class QuartoRequest {

	private int andar;
	private int noQuarto;
	private String situacao;
	private TipoQuarto tipoQuarto;
	
	Set<Comodidade> comodidade;
		
	public Set<Comodidade> getComodidade() {
		return comodidade;
	}
	public void setComodidade(Set<Comodidade> comodidade) {
		this.comodidade = comodidade;
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
	public TipoQuarto getTipoQuarto() {
		return tipoQuarto;
	}
	public void setTipoQuarto(TipoQuarto tipoQuarto) {
		this.tipoQuarto = tipoQuarto;
	}
	
	
	
	
}

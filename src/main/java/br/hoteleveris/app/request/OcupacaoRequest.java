package br.hoteleveris.app.request;

//import org.springframework.beans.factory.annotation.Value;

public class OcupacaoRequest {
	
	private String data;
	private Long qtdDiarias;
	private Long clienteId;
	private Long quartoId;
	
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
	
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	public Long getQuartoId() {
		return quartoId;
	}
	public void setQuartoId(Long quartoId) {
		this.quartoId = quartoId;
	}

}

package br.hoteleveris.app.response;
import java.util.List;

import br.hoteleveris.app.model.TipoQuarto;

public class ListTipoQuartoResponse extends BaseResponse{
	
	public List<TipoQuarto> tipos;

	public List<TipoQuarto> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoQuarto> tipos) {
		this.tipos = tipos;
	}
	
}

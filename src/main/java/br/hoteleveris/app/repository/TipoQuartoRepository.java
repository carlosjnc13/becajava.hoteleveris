package br.hoteleveris.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.hoteleveris.app.model.TipoQuarto;

public interface TipoQuartoRepository extends JpaRepository <TipoQuarto,Long> {

}

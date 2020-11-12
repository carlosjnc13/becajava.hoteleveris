package br.hoteleveris.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.hoteleveris.app.model.Quarto;

public interface QuartoRespository extends JpaRepository <Quarto,Long> {

}

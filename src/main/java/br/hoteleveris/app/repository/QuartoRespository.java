package br.hoteleveris.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.hoteleveris.app.model.Quarto;


@Repository
public interface QuartoRespository extends JpaRepository <Quarto,Long> {

}

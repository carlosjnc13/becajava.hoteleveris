package br.hoteleveris.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.hoteleveris.app.model.Comodidade;

public interface ComodidadeRepository extends JpaRepository<Comodidade,Long> {

}

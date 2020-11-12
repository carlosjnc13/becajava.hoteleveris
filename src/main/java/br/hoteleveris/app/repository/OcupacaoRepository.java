package br.hoteleveris.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.hoteleveris.app.model.Ocupacao;

public interface OcupacaoRepository extends JpaRepository<Ocupacao,Long> {

}

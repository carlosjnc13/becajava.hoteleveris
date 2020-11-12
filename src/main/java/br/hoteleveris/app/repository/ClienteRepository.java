package br.hoteleveris.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.hoteleveris.app.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}

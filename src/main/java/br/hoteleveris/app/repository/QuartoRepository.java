package br.hoteleveris.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.hoteleveris.app.model.Quarto;


@Repository
public interface QuartoRepository extends JpaRepository <Quarto,Long> {
	
//	@Query(value = "EXEC SP_BuscarTipoQuarto :tipoQuartoId", nativeQuery = true)
//	List<Quarto> findBuscarQuartos(@Param("tipoQuartoId") Long tipoQuartoId);
	
	@Query(value = "SELECT * FROM Quarto WHERE tipoQuartoId = :id", nativeQuery = true)
	List<Quarto> findBuscarQuartos(@Param("id") Long id);

}

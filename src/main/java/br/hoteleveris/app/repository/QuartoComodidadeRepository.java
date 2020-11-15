package br.hoteleveris.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.hoteleveris.app.model.Comodidade;
import br.hoteleveris.app.model.QuartoComodidade;


@Repository
public interface QuartoComodidadeRepository extends JpaRepository<QuartoComodidade,Long>{
	
	@Query(value = "EXEC SP_pegarComodidades :quartoId", nativeQuery = true)
	public List<Comodidade> findComodidades(@Param("quartoId") Long quartoId);
}

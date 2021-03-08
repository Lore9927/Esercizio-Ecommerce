package it.objectmethod.ecommerce.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.objectmethod.ecommerce.entity.Articolo;

@Repository
public interface ArticoloRepository extends JpaRepository<Articolo, Long> {
	
	@Query(value = "SELECT a FROM Articolo a WHERE (a.nomeArticolo LIKE CONCAT('%', ?1, '%') OR '' = ?1) AND (a.codiceArticolo = ?2 OR '' = ?2)")
	public List<Articolo> findByNameOrCode(String nome, String codice);
	
	
	public Articolo findByCodiceArticolo(String codice);
}

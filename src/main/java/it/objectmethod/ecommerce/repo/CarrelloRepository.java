package it.objectmethod.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.objectmethod.ecommerce.entity.Carrello;

@Repository
public interface CarrelloRepository extends JpaRepository<Carrello, Long> {
	
	@Query(value = "SELECT c FROM Carrello c WHERE c.utente.id = ?1")
	public Carrello findByIdUtente(Long idUtente);

}

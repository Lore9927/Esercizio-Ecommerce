package it.objectmethod.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.ecommerce.entity.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente,Long> {

	public Utente findByNomeAndPassword(String nomeUtente, String password);
	
	

	
}

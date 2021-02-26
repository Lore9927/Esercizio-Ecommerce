package it.objectmethod.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.ecommerce.entity.Ordine;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> {

}

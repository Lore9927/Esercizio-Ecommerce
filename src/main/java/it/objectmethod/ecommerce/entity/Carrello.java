package it.objectmethod.ecommerce.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carrello")
public class Carrello {
	
	@GeneratedValue
	@Id
	@Column(name = "id_carrello")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "id_utente")
	private Utente utente;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_carrello")
	private List<CarrelloDettaglio> dettagli;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public List<CarrelloDettaglio> getDettagli() {
		return dettagli;
	}

	public void setDettagli(List<CarrelloDettaglio> dettagli) {
		this.dettagli = dettagli;
	}
	
	
	
}

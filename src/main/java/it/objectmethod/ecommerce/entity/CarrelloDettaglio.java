package it.objectmethod.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "carrello_dettaglio")
public class CarrelloDettaglio {
	
	@GeneratedValue
	@Id
	@Column(name = "id_carrello_dettaglio")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_carrello")
	private Carrello carrello;
	
	
	@ManyToOne
	@JoinColumn(name = "id_articolo")
	private Articolo articolo;
	
	private Integer quantita;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Carrello getCarrello() {
		return carrello;
	}

	public void setCarrello(Carrello carrello) {
		this.carrello = carrello;
	}

	public Articolo getArticolo() {
		return articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	

}

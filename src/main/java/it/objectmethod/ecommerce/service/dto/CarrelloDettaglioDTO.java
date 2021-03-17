package it.objectmethod.ecommerce.service.dto;

import it.objectmethod.ecommerce.entity.Articolo;

public class CarrelloDettaglioDTO {
	private Long id;
	private Articolo articolo;
	private Integer quantita;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	
	
}

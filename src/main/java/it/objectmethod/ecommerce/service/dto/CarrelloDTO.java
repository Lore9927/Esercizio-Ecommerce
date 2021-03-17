package it.objectmethod.ecommerce.service.dto;

import java.util.List;

public class CarrelloDTO {
	private Long id;
	private Long idUtente;
	private String nomeUtente;
	private List<CarrelloDettaglioDTO> dettagli;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}
	public String getNomeUtente() {
		return nomeUtente;
	}
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	public List<CarrelloDettaglioDTO> getDettagli() {
		return dettagli;
	}
	public void setDettagli(List<CarrelloDettaglioDTO> dettagli) {
		this.dettagli = dettagli;
	}
	
	
	
}

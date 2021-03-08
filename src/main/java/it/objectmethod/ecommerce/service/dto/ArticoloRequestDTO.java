package it.objectmethod.ecommerce.service.dto;

import org.springframework.stereotype.Component;

@Component
public class ArticoloRequestDTO {
	private String codiceArticolo;
	private int quantita;
	
	public String getCodiceArticolo() {
		return codiceArticolo;
	}
	public void setCodiceArticolo(String codiceArticolo) {
		this.codiceArticolo = codiceArticolo;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	
	
	
}

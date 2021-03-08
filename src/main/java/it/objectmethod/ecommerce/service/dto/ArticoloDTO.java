package it.objectmethod.ecommerce.service.dto;

public class ArticoloDTO {
	private Long id;
	private String codiceArticolo;
	private String nomeArticolo;
	private Integer disponibilita;
	private double prezzoUnitario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodiceArticolo() {
		return codiceArticolo;
	}
	public void setCodiceArticolo(String codiceArticolo) {
		this.codiceArticolo = codiceArticolo;
	}
	public String getNomeArticolo() {
		return nomeArticolo;
	}
	public void setNomeArticolo(String nomeArticolo) {
		this.nomeArticolo = nomeArticolo;
	}
	public Integer getDisponibilita() {
		return disponibilita;
	}
	public void setDisponibilita(Integer disponibilita) {
		this.disponibilita = disponibilita;
	}
	public double getPrezzoUnitario() {
		return prezzoUnitario;
	}
	public void setPrezzoUnitario(double prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}
	
	
}

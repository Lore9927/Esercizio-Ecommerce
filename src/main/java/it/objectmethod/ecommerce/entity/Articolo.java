package it.objectmethod.ecommerce.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "articolo")
public class Articolo {
	
	@GeneratedValue
	@Id
	@Column(name = "id_articolo")
	private Long id;
	
	@Column(name = "codice_articolo")
	private String codiceArticolo;
	
	@Column(name = "nome_articolo")
	private String nomeArticolo;
	
	@Column(name = "disponibilita")
	private int disponibilita;
	
	@Column(name = "prezzo_unitario")
	private double prezzoUnitario;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "riga_ordine", joinColumns = @JoinColumn(name = "id_articolo", referencedColumnName = "id_articolo"), inverseJoinColumns = @JoinColumn(name = "id_ordine", referencedColumnName = "id_ordine"))
	private List<Ordine> ordini;
	
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

	public int getDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}

	public double getPrezzoUnitario() {
		return prezzoUnitario;
	}

	public void setPrezzoUnitario(double prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}

	public List<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}
	
	

}

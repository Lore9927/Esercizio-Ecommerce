package it.objectmethod.ecommerce.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ordine")
public class Ordine {
	
	@GeneratedValue
	@Id
	@Column(name = "id_ordine")
	private Long id;
	
	@Column(name = "numero_ordine")
	private String numeroOrdine;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_utente")
	private Utente utente;
	
	@ManyToMany
	@JoinTable(name = "riga_ordine", joinColumns = @JoinColumn(name = "id_ordine", referencedColumnName = "id_ordine"), inverseJoinColumns = @JoinColumn(name = "id_articolo", referencedColumnName = "id_articolo"))
	private List<Articolo> articoli;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(String numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public List<Articolo> getArticoli() {
		return articoli;
	}

	public void setArticoli(List<Articolo> articoli) {
		this.articoli = articoli;
	}
	
	

}

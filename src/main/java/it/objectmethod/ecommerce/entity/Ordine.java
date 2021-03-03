package it.objectmethod.ecommerce.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@Column(name = "data_ordine")
	private Date dataOrdine;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_utente")
	private Utente utente;
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_ordine")
	private List<RigaOrdine> righe;
	
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

	public List<RigaOrdine> getRighe() {
		return righe;
	}

	public void setRighe(List<RigaOrdine> righe) {
		this.righe = righe;
	}

	public Date getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}
	
	

}

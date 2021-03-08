package it.objectmethod.ecommerce.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.ecommerce.entity.Articolo;
import it.objectmethod.ecommerce.entity.Carrello;
import it.objectmethod.ecommerce.entity.CarrelloDettaglio;
import it.objectmethod.ecommerce.entity.Ordine;
import it.objectmethod.ecommerce.entity.RigaOrdine;
import it.objectmethod.ecommerce.entity.Utente;
import it.objectmethod.ecommerce.repo.ArticoloRepository;
import it.objectmethod.ecommerce.repo.CarrelloRepository;
import it.objectmethod.ecommerce.repo.OrdineRepository;
import it.objectmethod.ecommerce.service.dto.OrdineDTO;
import it.objectmethod.ecommerce.service.dto.UtenteDTO;
import it.objectmethod.ecommerce.service.mapper.OrdineMapper;
import it.objectmethod.ecommerce.service.mapper.UtenteMapper;

@Component
public class OrdineService {
	
	@Autowired
	OrdineMapper ordineMapper;
	
	@Autowired
	UtenteMapper utenteMapper;
	
	@Autowired 
	OrdineRepository ordineRepo;
	
	@Autowired
	CarrelloRepository carrelloRepo;
	
	@Autowired
	ArticoloRepository articoloRepo;
	
	public OrdineDTO createOrder(UtenteDTO u) {
		Utente utente = utenteMapper.toEntity(u);
		OrdineDTO ordineDTO = null;
		boolean errore = false;
		Carrello carrello = carrelloRepo.findByIdUtente(utente.getId());
		if (carrello != null) {
			Ordine ordine = new Ordine();
			List<RigaOrdine> righe = new ArrayList<RigaOrdine>();
			for (CarrelloDettaglio c : carrello.getDettagli()) {
				Articolo a = c.getArticolo();
				int nuovaDisp = a.getDisponibilita() - c.getQuantita();
				if (nuovaDisp >= 0) {
					RigaOrdine riga = new RigaOrdine();
					a.setDisponibilita(nuovaDisp);
					riga.setQuantita(c.getQuantita());
					riga.setArticolo(a);
					righe.add(riga);
					articoloRepo.save(a);
				} else {
					errore = true;
					break;
				}

			}
			if (!errore) {
				LocalDate dataOrdine = LocalDate.now();
				ordine.setDataOrdine(dataOrdine);
				ordine.setRighe(righe);
				ordine.setNumeroOrdine("A000" + carrello.getId());
				ordine.setUtente(carrello.getUtente());

				ordine = ordineRepo.save(ordine);
				ordineDTO = ordineMapper.toDto(ordine);
				carrelloRepo.deleteById(carrello.getId());
				
			}

		} 		
		return ordineDTO;
	}
	


}

package it.objectmethod.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.ecommerce.entity.Articolo;
import it.objectmethod.ecommerce.entity.Carrello;
import it.objectmethod.ecommerce.entity.CarrelloDettaglio;
import it.objectmethod.ecommerce.entity.Utente;
import it.objectmethod.ecommerce.repo.ArticoloRepository;
import it.objectmethod.ecommerce.repo.CarrelloRepository;
import it.objectmethod.ecommerce.repo.UtenteRepository;
import it.objectmethod.ecommerce.service.dto.CarrelloDTO;
import it.objectmethod.ecommerce.service.dto.UtenteDTO;
import it.objectmethod.ecommerce.service.mapper.CarrelloMapper;
import it.objectmethod.ecommerce.service.mapper.UtenteMapper;

@Component
public class CarrelloService {

	@Autowired
	CarrelloMapper carrelloMapper;

	@Autowired
	CarrelloRepository carrelloRep;

	@Autowired
	ArticoloRepository articoloRep;

	@Autowired
	UtenteRepository utenteRep;

	@Autowired
	UtenteMapper utenteMapper;

	public CarrelloDTO checkCart(UtenteDTO utenteDTO) {
		Utente utente = utenteMapper.toEntity(utenteDTO);
		Carrello carrello = carrelloRep.findByIdUtente(utente.getId());
		CarrelloDTO carrelloDTO = carrelloMapper.toDto(carrello);
		return carrelloDTO;
	}
	public CarrelloDTO addItems(String codiceArticolo, Integer quantita, UtenteDTO utenteDTO) {
		CarrelloDTO carrelloDTO = null;
		boolean errore = false;
		Articolo articolo = articoloRep.findByCodiceArticolo(codiceArticolo);
		if (articolo != null && articolo.getDisponibilita() > quantita) {
			Utente utente = utenteMapper.toEntity(utenteDTO);
			boolean trovato = false;
			Carrello carrello = carrelloRep.findByIdUtente(utente.getId());
			if (carrello == null) {
				carrello = new Carrello();
				utente = utenteRep.findById(utente.getId()).get();
				carrello.setUtente(utente);

			}

			List<CarrelloDettaglio> dettagli;

			if (carrello.getDettagli() == null) {
				dettagli = new ArrayList<CarrelloDettaglio>();
			} else {
				dettagli = carrello.getDettagli();
			}

			for (CarrelloDettaglio det : dettagli) {
				if (articolo.getCodiceArticolo().equals(det.getArticolo().getCodiceArticolo())) {
					int somma = det.getQuantita() + quantita;
					if (somma <= det.getArticolo().getDisponibilita()) {
						det.setQuantita(somma);
						trovato = true;
						break;
					} else {
						errore = true;
						break;
					}

				}
			}
			if (!errore) {
				if (!trovato) {
					CarrelloDettaglio dettaglio = new CarrelloDettaglio();
					dettaglio.setArticolo(articolo);
					dettaglio.setQuantita(quantita);
					dettagli.add(dettaglio);
				}

				carrello.setDettagli(dettagli);

				carrello = carrelloRep.save(carrello);
				carrelloDTO = carrelloMapper.toDto(carrello);

			}

		}
		return carrelloDTO;
	}
}

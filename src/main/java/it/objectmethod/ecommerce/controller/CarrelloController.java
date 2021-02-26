package it.objectmethod.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.entity.Articolo;
import it.objectmethod.ecommerce.entity.Carrello;
import it.objectmethod.ecommerce.entity.CarrelloDettaglio;
import it.objectmethod.ecommerce.entity.Utente;
import it.objectmethod.ecommerce.repo.ArticoloRepository;
import it.objectmethod.ecommerce.repo.CarrelloRepository;
import it.objectmethod.ecommerce.repo.UtenteRepository;

@RestController
@RequestMapping("/api/carrello")
public class CarrelloController {

	@Autowired
	CarrelloRepository carrelloRep;

	@Autowired
	ArticoloRepository articoloRep;

	@Autowired
	UtenteRepository utenteRep;

	@RequestMapping("/get")
	public Carrello getCarrello(@RequestParam("id") Long id) {
		Carrello c = carrelloRep.findById(id).get();
		return c;
	}

	@PutMapping("/add")
	public ResponseEntity<Carrello> addItemsToCart(@RequestParam("codiceArticolo") String codiceArticolo,
			@RequestParam("qta") int quantita, @RequestParam("idUtente") Long idUtente) {

		ResponseEntity<Carrello> resp = null;
		Articolo articolo = articoloRep.findByCodiceArticolo(codiceArticolo);
		if (articolo != null && articolo.getDisponibilita() > quantita) {
			boolean trovato = false;
			Carrello carrello = carrelloRep.findByIdUtente(idUtente);
			if (carrello == null) {
				carrello = new Carrello();
				Utente utente = utenteRep.findById(idUtente).get();
				carrello.setUtente(utente);

			}

			CarrelloDettaglio dettaglio = new CarrelloDettaglio();
			List<CarrelloDettaglio> dettagli;

			if (carrello.getDettagli() == null) {
				dettagli = new ArrayList<CarrelloDettaglio>();
			} else {
				dettagli = carrello.getDettagli();
			}

			for (CarrelloDettaglio det : dettagli) {
				if (det.getArticolo().getCodiceArticolo().equals(codiceArticolo)) {
					int somma = det.getQuantita() + quantita;
					if (somma <= det.getArticolo().getDisponibilita()) {
						det.setQuantita(somma);
						trovato = true;
					} else {
						resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
					}

				}
			}
			if (resp == null) {
				if (!trovato) {
					dettaglio.setArticolo(articolo);
					dettaglio.setQuantita(quantita);
					dettagli.add(dettaglio);
				}

				carrello.setDettagli(dettagli);

				carrello = carrelloRep.save(carrello);
				resp = new ResponseEntity<>(carrello, HttpStatus.OK);
			}

		} else {
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return resp;
	}

}

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
import it.objectmethod.ecommerce.entity.Ordine;
import it.objectmethod.ecommerce.repo.ArticoloRepository;
import it.objectmethod.ecommerce.repo.CarrelloRepository;
import it.objectmethod.ecommerce.repo.OrdineRepository;

@RestController
@RequestMapping("/api/ordine")
public class OrdineController {

	@Autowired
	OrdineRepository ordineRepo;

	@Autowired
	CarrelloRepository carrelloRepo;
	
	@Autowired
	ArticoloRepository articoloRepo;

	@PutMapping("/add")
	public ResponseEntity<Ordine> createOrder(@RequestParam("idUtente") Long idUtente) {
		ResponseEntity<Ordine> resp = null;
		Carrello carrello = carrelloRepo.findByIdUtente(idUtente);
		if (carrello != null) {
			Ordine ordine = new Ordine();
			List<Articolo> articoli = new ArrayList<Articolo>();
			for (CarrelloDettaglio c : carrello.getDettagli()) {
				Articolo a = c.getArticolo();
				int nuovaDisp = a.getDisponibilita() - c.getQuantita();
				if (nuovaDisp >= 0) {
					a.setDisponibilita(nuovaDisp);
					articoli.add(a);
					articoloRepo.save(a);
				} else {
					resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
					break;
				}

			}
			if (resp == null) {
				ordine.setArticoli(articoli);
				ordine.setNumeroOrdine("A000" + carrello.getId());
				ordine.setUtente(carrello.getUtente());

				ordine = ordineRepo.save(ordine);
				carrelloRepo.deleteById(carrello.getId());
				resp = new ResponseEntity<>(ordine, HttpStatus.OK);
			}

		} else {
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return resp;
	}
}

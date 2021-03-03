package it.objectmethod.ecommerce.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.controller.service.JWTService;
import it.objectmethod.ecommerce.entity.Articolo;
import it.objectmethod.ecommerce.entity.Carrello;
import it.objectmethod.ecommerce.entity.CarrelloDettaglio;
import it.objectmethod.ecommerce.entity.Ordine;
import it.objectmethod.ecommerce.entity.RigaOrdine;
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
	
	@Autowired
	JWTService jwtServ;

	@PutMapping("/add")
	public ResponseEntity<Ordine> createOrder(@RequestHeader("auth-token") String token) {
		ResponseEntity<Ordine> resp = null;
		boolean errore = false;
		Long idUtente = jwtServ.getIdUtenteByToken(token);
		Carrello carrello = carrelloRepo.findByIdUtente(idUtente);
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
				long millis = System.currentTimeMillis();  
				Date dataOrdine = new Date(millis);  
				
				ordine.setDataOrdine(dataOrdine);
				ordine.setRighe(righe);
				ordine.setNumeroOrdine("A000" + carrello.getId());
				ordine.setUtente(carrello.getUtente());

				ordine = ordineRepo.save(ordine);
				carrelloRepo.deleteById(carrello.getId());
				resp = new ResponseEntity<>(ordine, HttpStatus.OK);
			}

		} else {
			errore = true;
		}
		if(errore) {
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return resp;
	}
}

package it.objectmethod.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.controller.service.JWTService;
import it.objectmethod.ecommerce.service.CarrelloService;
import it.objectmethod.ecommerce.service.dto.CarrelloDTO;
import it.objectmethod.ecommerce.service.dto.UtenteDTO;

@RestController
@RequestMapping("/api/carrello")
public class CarrelloController {

	@Autowired
	CarrelloService carrelloServ;

	@Autowired
	JWTService jwtServ;

	@PutMapping("/add")
	public ResponseEntity<CarrelloDTO> addItemsToCart(@RequestParam("codiceArticolo") String codiceArticolo,
			@RequestParam("quantita") Integer quantita, @RequestHeader("auth-token") String token) {

		ResponseEntity<CarrelloDTO> resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		UtenteDTO utenteDTO = jwtServ.getUtenteByToken(token);
		if (utenteDTO != null) {
			CarrelloDTO carrelloDTO = carrelloServ.addItems(codiceArticolo, quantita, utenteDTO);
			if (carrelloDTO != null) {
				resp = new ResponseEntity<>(carrelloDTO, HttpStatus.ACCEPTED);
			}
		}

		return resp;
	}

}

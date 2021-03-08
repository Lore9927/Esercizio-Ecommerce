package it.objectmethod.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.service.CarrelloService;
import it.objectmethod.ecommerce.service.dto.ArticoloRequestDTO;
import it.objectmethod.ecommerce.service.dto.CarrelloDTO;

@RestController
@RequestMapping("/api/carrello")
public class CarrelloController {

	@Autowired
	CarrelloService carrelloServ;

	@PutMapping("/add")
	public ResponseEntity<CarrelloDTO> addItemsToCart(@RequestBody ArticoloRequestDTO articoloDTO, @RequestHeader("auth-token") String token) {

		ResponseEntity<CarrelloDTO> resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		CarrelloDTO carrelloDTO = carrelloServ.addItems(articoloDTO, token);
		if(carrelloDTO != null) {
			resp = new ResponseEntity<>(carrelloDTO,HttpStatus.ACCEPTED);
		}
		return resp;
	}

}

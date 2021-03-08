package it.objectmethod.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.controller.service.JWTService;
import it.objectmethod.ecommerce.service.OrdineService;
import it.objectmethod.ecommerce.service.dto.OrdineDTO;
import it.objectmethod.ecommerce.service.dto.UtenteDTO;

@RestController
@RequestMapping("/api/ordine")
public class OrdineController {

	@Autowired
	OrdineService ordineServ;
	
	@Autowired
	JWTService jwtServ;

	@PutMapping("/add")
	public ResponseEntity<OrdineDTO> createOrder(@RequestHeader("auth-token") String token) {
		ResponseEntity<OrdineDTO> resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		UtenteDTO utenteDTO = jwtServ.getIdUtenteByToken(token);
		OrdineDTO ordineDTO = ordineServ.createOrder(utenteDTO);
		if(ordineDTO != null) {
			resp = new ResponseEntity<>(ordineDTO,HttpStatus.ACCEPTED);
		}
		return resp;
	}
}

package it.objectmethod.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.controller.service.JWTService;
import it.objectmethod.ecommerce.service.UtenteService;
import it.objectmethod.ecommerce.service.dto.UtenteDTO;
import it.objectmethod.ecommerce.service.dto.UtenteRequestDTO;


@RestController
@RequestMapping("/api/utente")
public class UtenteController {
	
	@Autowired
	private UtenteService utenteServ;
	
	@Autowired
	private JWTService jwtServ;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UtenteRequestDTO u ) {
		String token;
		ResponseEntity<String> resp = new ResponseEntity<>("Nome utente o password errati", HttpStatus.BAD_REQUEST);
		
		UtenteDTO utente = utenteServ.findUserByNomeAndPassword(u);
		if(utente != null)
		{
			token = jwtServ.createJWTToken(utente);
			resp = new ResponseEntity<>(token, HttpStatus.ACCEPTED);
		}
		
		return resp;
	}

}

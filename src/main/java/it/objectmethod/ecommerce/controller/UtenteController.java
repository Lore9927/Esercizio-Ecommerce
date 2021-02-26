package it.objectmethod.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.entity.Utente;
import it.objectmethod.ecommerce.repo.UtenteRepository;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {
	
	@Autowired
	private UtenteRepository utenteRep;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Utente u ) {
		String nome = u.getNome();
		String password = u.getPassword();
		ResponseEntity<String> resp = new ResponseEntity<>("Nome utente o password errati", HttpStatus.BAD_REQUEST);
		
		Utente utente = utenteRep.findByNomeAndPassword(nome, password);
		if(utente != null)
		{
			resp = new ResponseEntity<>("Benvenuto " + nome, HttpStatus.ACCEPTED);
		}
		
		return resp;
	}

}

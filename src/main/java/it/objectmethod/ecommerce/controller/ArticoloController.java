package it.objectmethod.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.entity.Articolo;
import it.objectmethod.ecommerce.repo.ArticoloRepository;

@RestController
@RequestMapping("/api/articoli")
public class ArticoloController {
	
	@Autowired
	private ArticoloRepository articoliRepo;
	
	@GetMapping("/find")
	public List<Articolo> find(@RequestParam("nome") String nome, @RequestParam("codice") String codice) {
		List<Articolo> a = articoliRepo.findByNameOrCode(nome, codice);
		return a;
	}

}

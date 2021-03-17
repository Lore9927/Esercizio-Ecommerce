package it.objectmethod.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.service.ArticoloService;
import it.objectmethod.ecommerce.service.dto.ArticoloDTO;

@RestController
@RequestMapping("/api/articoli")
public class ArticoloController {
	
	@Autowired
	private ArticoloService articoloServ;
	
	@GetMapping("/find")
	public List<ArticoloDTO> find(@RequestBody ArticoloDTO art) {
		List<ArticoloDTO> articoliDTO = articoloServ.findItemByNameOrCode(art);
		return articoliDTO;
	}
	
	@GetMapping("/find-all")
	public List<ArticoloDTO> find() {
		List<ArticoloDTO> articoliDTO = articoloServ.findAll();
		return articoliDTO;
	}

}

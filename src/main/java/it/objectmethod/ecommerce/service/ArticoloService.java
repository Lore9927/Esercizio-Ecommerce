package it.objectmethod.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.ecommerce.entity.Articolo;
import it.objectmethod.ecommerce.repo.ArticoloRepository;
import it.objectmethod.ecommerce.service.dto.ArticoloDTO;
import it.objectmethod.ecommerce.service.mapper.ArticoloMapper;

@Component
public class ArticoloService {
	
	@Autowired
	ArticoloMapper articoloMapper;
	
	@Autowired
	private ArticoloRepository articoliRepo;
	
	public List<ArticoloDTO> findItemByNameOrCode(ArticoloDTO articoloDTO) {
		Articolo articolo = articoloMapper.toEntity(articoloDTO);
		List<Articolo> articoli = articoliRepo.findByNameOrCode(articolo.getNomeArticolo(), articolo.getCodiceArticolo());
		List<ArticoloDTO> articoliDTO = articoloMapper.toDto(articoli);
		return articoliDTO;
	}
	
	public List<ArticoloDTO> findAll() {
		List<Articolo> articoli = articoliRepo.findAll();
		List<ArticoloDTO> articoliDTO = articoloMapper.toDto(articoli);
		
		return articoliDTO;
	}
}

package it.objectmethod.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.ecommerce.entity.Utente;
import it.objectmethod.ecommerce.repo.UtenteRepository;
import it.objectmethod.ecommerce.service.dto.UtenteDTO;
import it.objectmethod.ecommerce.service.dto.UtenteRequestDTO;
import it.objectmethod.ecommerce.service.mapper.UtenteMapper;
import it.objectmethod.ecommerce.service.mapper.UtenteRequestMapper;

@Component
public class UtenteService {
	
	@Autowired
	private UtenteRepository utenteRep;
	
	@Autowired
	private UtenteMapper utenteMapper;
	
	@Autowired
	private UtenteRequestMapper utenteReqMapper;
	
	public UtenteDTO findUserByNomeAndPassword(UtenteRequestDTO u) {
		Utente utente = utenteReqMapper.toEntity(u);
		UtenteDTO utenteDTO = null;
		String nome = utente.getNome();
		String password = utente.getPassword();
		
		utente = utenteRep.findByNomeAndPassword(nome, password);
		
		if(utente != null) {
			utenteDTO = utenteMapper.toDto(utente);
		}
		
		return utenteDTO;
	}
}

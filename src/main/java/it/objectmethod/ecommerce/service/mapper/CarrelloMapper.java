package it.objectmethod.ecommerce.service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.objectmethod.ecommerce.entity.Carrello;
import it.objectmethod.ecommerce.service.dto.CarrelloDTO;

@Mapper(componentModel = "spring", uses = {CarrelloDettaglioMapper.class})
public interface CarrelloMapper extends EntityMapper<CarrelloDTO, Carrello> {
	@Override
	@Mapping(source = "utente.id", target = "idUtente")
	@Mapping(source = "utente.nome", target = "nomeUtente")
	CarrelloDTO toDto(Carrello entity);
}

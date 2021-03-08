package it.objectmethod.ecommerce.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.objectmethod.ecommerce.entity.Carrello;
import it.objectmethod.ecommerce.service.dto.CarrelloDTO;

@Mapper(componentModel = "spring")
public interface CarrelloMapper extends EntityMapper<CarrelloDTO, Carrello> {
	@Override
	@Mapping(source = "utente.id", target = "idUtente")
	@Mapping(source = "utente.nome", target = "nomeUtente")
	CarrelloDTO toDto(Carrello entity);
	
	@Override
	@Mapping(target = "dettagli", ignore = true)
	Carrello toEntity(CarrelloDTO dto);
	
	@Override
	List<CarrelloDTO> toDto(List<Carrello> entityList);
	
	@Override
	List<Carrello> toEntity(List<CarrelloDTO> dtoList);

}

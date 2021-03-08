package it.objectmethod.ecommerce.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.objectmethod.ecommerce.entity.Utente;
import it.objectmethod.ecommerce.service.dto.UtenteDTO;

@Mapper(componentModel = "spring")
public interface UtenteMapper extends EntityMapper<UtenteDTO, Utente> {

	@Override
	UtenteDTO toDto(Utente entity);
	
	@Override
	@Mapping(target = "password", ignore = true)
	Utente toEntity(UtenteDTO dto);
}

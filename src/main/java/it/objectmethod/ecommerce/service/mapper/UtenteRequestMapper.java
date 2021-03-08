package it.objectmethod.ecommerce.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.objectmethod.ecommerce.entity.Utente;
import it.objectmethod.ecommerce.service.dto.UtenteRequestDTO;

@Mapper(componentModel = "spring")
public interface UtenteRequestMapper extends EntityMapper<UtenteRequestDTO, Utente> {
	
	@Override
	@Mapping(source = "id", target = "utenteDTO.id")
	@Mapping(source = "nome", target = "utenteDTO.nome")
	UtenteRequestDTO toDto(Utente entity);
	
	@Override
	@Mapping(source = "utenteDTO.id", target = "id")
	@Mapping(source = "utenteDTO.nome", target = "nome")
	Utente toEntity(UtenteRequestDTO dto);
	
	@Override
	List<UtenteRequestDTO> toDto(List<Utente> entityList);
	
	@Override
	List<Utente> toEntity(List<UtenteRequestDTO> dtoList);

}

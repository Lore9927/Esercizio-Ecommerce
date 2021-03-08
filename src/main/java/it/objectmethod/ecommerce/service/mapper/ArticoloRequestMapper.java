package it.objectmethod.ecommerce.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.objectmethod.ecommerce.entity.Articolo;
import it.objectmethod.ecommerce.service.dto.ArticoloRequestDTO;

@Mapper(componentModel = "spring")
public interface ArticoloRequestMapper extends EntityMapper<ArticoloRequestDTO, Articolo> {
	@Override
	ArticoloRequestDTO toDto(Articolo entity);
	
	@Override
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "nomeArticolo", ignore = true)
	@Mapping(target = "disponibilita", ignore = true)
	@Mapping(target = "prezzoUnitario", ignore = true)
	Articolo toEntity(ArticoloRequestDTO dto);
	
	@Override
	List<ArticoloRequestDTO> toDto(List<Articolo> entityList);
	
	@Override
	List<Articolo> toEntity(List<ArticoloRequestDTO> dtoList);

}

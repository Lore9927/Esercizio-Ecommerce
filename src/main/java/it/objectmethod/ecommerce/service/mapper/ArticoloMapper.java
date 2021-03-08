package it.objectmethod.ecommerce.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import it.objectmethod.ecommerce.entity.Articolo;
import it.objectmethod.ecommerce.service.dto.ArticoloDTO;

@Mapper(componentModel = "spring")
public interface ArticoloMapper extends EntityMapper<ArticoloDTO, Articolo> {
	@Override
	ArticoloDTO toDto(Articolo entity);

	@Override
	Articolo toEntity(ArticoloDTO dto);

	@Override
	List<ArticoloDTO> toDto(List<Articolo> entityList);

	@Override
	List<Articolo> toEntity(List<ArticoloDTO> dtoList);

}

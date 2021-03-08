package it.objectmethod.ecommerce.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.objectmethod.ecommerce.entity.Ordine;
import it.objectmethod.ecommerce.service.dto.OrdineDTO;

@Mapper(componentModel = "spring")
public interface OrdineMapper extends EntityMapper<OrdineDTO, Ordine> {
	
	@Override
	@Mapping(source = "utente.id", target = "idUtente")
	@Mapping(source = "utente.nome", target = "nomeUtente")
	OrdineDTO toDto(Ordine entity);
	
	@Override
	@Mapping(target = "utente", ignore = true)
	@Mapping(target = "righe", ignore = true)
	Ordine toEntity(OrdineDTO dto);

}

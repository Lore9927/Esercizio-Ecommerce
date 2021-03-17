package it.objectmethod.ecommerce.service.mapper;

import org.mapstruct.Mapper;

import it.objectmethod.ecommerce.entity.CarrelloDettaglio;
import it.objectmethod.ecommerce.service.dto.CarrelloDettaglioDTO;

@Mapper(componentModel = "spring")
public interface CarrelloDettaglioMapper extends EntityMapper<CarrelloDettaglioDTO, CarrelloDettaglio> {

}

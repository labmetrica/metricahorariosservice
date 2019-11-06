package com.metrica.formacion.metricahorariosservice.dto;

import com.metrica.formacion.metricahorariosservice.entity.grupos;
import com.metrica.formacion.metricahorariosservice.entity.usuarios;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface gruposMapper {

    gruposMapper INSTANCE = Mappers.getMapper(gruposMapper.class);

    @Mapping(source = "id",target = "id")
    @Mapping(source = "huecos", target = "huecos")
    gruposDTO togruposDTO(grupos grupos);

    List<gruposDTO> togruposDTOs(List<grupos> grupos);

    grupos togrupos(gruposDTO gruposDTO);
}

package com.metrica.formacion.metricahorariosservice.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface usuariosMapper {

    usuariosMapper INSTANCE = Mappers.getMapper(usuariosMapper.class);
}

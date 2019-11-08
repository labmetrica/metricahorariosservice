package com.metrica.formacion.metricahorariosservice.dto;

import com.metrica.formacion.metricahorariosservice.entity.usuarios;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface usuariosMapper {

    usuariosMapper INSTANCE = Mappers.getMapper(usuariosMapper.class);

    @Mapping(source = "id",target = "id")
    usuariosDTO tousuariosDTO(usuarios usuarios);

    List<usuariosDTO> tousuariosDTOs(List<usuarios> usuarios);

    usuarios tousuario(usuariosDTO usuariosDTO);
}

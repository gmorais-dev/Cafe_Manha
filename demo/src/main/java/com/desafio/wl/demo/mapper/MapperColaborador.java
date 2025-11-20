package com.desafio.wl.demo.mapper;


import com.desafio.wl.demo.DTO.ColaboradorDTO;
import com.desafio.wl.demo.Model.Colaborador;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring") // Indica que o MapStruct deve gerar um bean Spring para este mapper
public interface MapperColaborador {

    Colaborador toEntity(ColaboradorDTO dto);
    ColaboradorDTO toDTO(Colaborador entity);
}

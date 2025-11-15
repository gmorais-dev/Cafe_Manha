package com.desafio.wl.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CafeManhaDTO {
    private String nome;
    private String nomeColaborador;
    private String cpf;
    private String opcaoCafe;
    private LocalDate data;

}
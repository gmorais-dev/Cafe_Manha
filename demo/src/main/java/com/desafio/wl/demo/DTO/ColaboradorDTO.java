package com.desafio.wl.demo.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorDTO {
    private String nome;
    private String cpf;
    private String email;
}


package com.desafio.wl.demo.Model;


import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cafe_manha")

public class CafeManha {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;
    private String nomeColaborador;
    private String cpf;
    private String opcaoCafe;
    private LocalDate data;

}


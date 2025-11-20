package com.desafio.wl.demo.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "colaborador")
public class Colaborador {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;
    private String nome;
    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;
    @Column(name = "email", unique = true, nullable = false)
    private String email;


}

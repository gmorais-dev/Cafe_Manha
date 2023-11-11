package com.desafio.wl.demo.Repository;

import com.desafio.wl.demo.Model.CafeManha;
import com.desafio.wl.demo.Model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM cafe_manha WHERE cpf = ?1 AND data = ?2")
    CafeManha findByCpfAndData(String cpf, LocalDate data);
}


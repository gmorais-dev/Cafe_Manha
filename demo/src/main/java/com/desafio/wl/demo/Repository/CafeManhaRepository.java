package com.desafio.wl.demo.Repository;

import com.desafio.wl.demo.Model.CafeManha;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDate;
import java.util.Optional;

public interface CafeManhaRepository extends JpaRepository<CafeManha, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM cafe_manha WHERE cpf = ?1 AND data = ?2")
    CafeManha findByCpfAndData(String cpf, LocalDate data);


    Optional<CafeManha> findByNomeColaboradorAndDataAndOpcaoCafe(String nomeColaborador, LocalDate data, String opcaoCafe);

    public static Optional<CafeManha> findByCpfAndDataAndOpcaoCafe(String cpf, LocalDate data, String opcaoCafe) {
        return null;
    }

    boolean existsByCpf(String cpf);
}


package com.desafio.wl.demo.Repository;

import com.desafio.wl.demo.Model.CafeManha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

public interface CafeManhaRepository extends JpaRepository<CafeManha, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM CAFE_MANHA WHERE cpf = ?1 AND data = ?2")
    CafeManha findByCpfAndData(String cpf, LocalDate data);

    @Query(value = "SELECT * FROM ITEMS i WHERE i.ID = ?", nativeQuery = true)
    Optional<CafeManha> findOneById(Long id);

    @Query(value = "SELECT * FROM ITEMS i WHERE i.NAME = ?", nativeQuery = true)
    Optional<CafeManha> findOneByName(String name);

    boolean existsByCpf(String cpf);
    static boolean existsopcaoCafe(String opcaoCafe);







}


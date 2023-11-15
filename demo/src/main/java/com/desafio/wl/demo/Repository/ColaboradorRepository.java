package com.desafio.wl.demo.Repository;

import com.desafio.wl.demo.Model.CafeManha;
import com.desafio.wl.demo.Model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM /api/colaboradores WHERE cpf = ?1 AND data = ?2")
    CafeManha findByCpfAndData(String cpf, LocalDate data);

    @Query(value = "SELECT * FROM COLABORADORES c WHERE c.CPF = ?", nativeQuery = true)
    Optional<Colaborador> findOneByCpf(String cpf);

    @Modifying
    @Transactional
    @Query(value = "UPDATE COLABORADORES SET FULL_NAME = ?2, CPF = ?3 WHERE ID = ?1", nativeQuery = true)
    void updateOneById(Long id, String fullName, String cpf);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM COLABORADORES WHERE ID = ?", nativeQuery = true)
    void deleteOneById(Long id);

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM colaborador", nativeQuery = true)
    List<Colaborador> findAllColaboradores();


    Optional<Colaborador> findById(Long id);

    boolean existsByCpf(String cpf);

}

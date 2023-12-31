package com.desafio.wl.demo.Services;

import com.desafio.wl.demo.Model.CafeManha;
import com.desafio.wl.demo.Repository.CafeManhaRepository;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CafeManhaService {

    private final CafeManhaRepository cafeManhaRepository;

    public CafeManhaService(CafeManhaRepository cafeManhaRepository) {
        this.cafeManhaRepository = cafeManhaRepository;
    }

    public ResponseEntity<String> adicionarCafe(CafeManha cafeManha) {

        try {
            cafeManhaRepository.save(cafeManha);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registro de café da manhã adicionado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao adicionar registro de café da manhã.");
        }
    }

    public ResponseEntity<String> atualizarCafeManha(CafeManha atualizarCafeManha) {
        Optional<CafeManha> cafeExistente = cafeManhaRepository.findById(atualizarCafeManha.getId());
        if (cafeExistente.isPresent()) {
            cafeManhaRepository.save(atualizarCafeManha);
            return ResponseEntity.status(HttpStatus.OK).body("Café da manhã atualizado com sucesso");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Café da manhã não encontrado.");
        }
    }

    public ResponseEntity<String> excluirCafeManha(long id) {
        Optional<CafeManha> cafeExistente = cafeManhaRepository.findById(id);

        if (cafeExistente.isPresent()) {
            try {
                cafeManhaRepository.delete(cafeExistente.get());
                return ResponseEntity.status(HttpStatus.OK).body("Registro de café da manhã excluído com sucesso.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erro ao excluir registro de café da manhã.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Registro de café da manhã não encontrado para exclusão.");
        }
    }
    public ResponseEntity<List<CafeManha>> listarCafeManha() {
        List<CafeManha> listarCafeManha = cafeManhaRepository.findAll();
        if (listarCafeManha.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listarCafeManha);
        } else {
            return ResponseEntity.ok().body(listarCafeManha);
        }


        }

    }

















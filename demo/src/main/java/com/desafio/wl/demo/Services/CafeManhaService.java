package com.desafio.wl.demo.Services;

import com.desafio.wl.demo.Model.CafeManha;
import com.desafio.wl.demo.Model.Colaborador;
import com.desafio.wl.demo.Repository.CafeManhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CafeManhaService {

    private final CafeManhaRepository cafeManhaRepository;

    @Autowired
    public CafeManhaService(CafeManhaRepository cafeManhaRepository) {
        this.cafeManhaRepository = cafeManhaRepository;
    }

    public ResponseEntity<String> adicionarCafeManha(CafeManha cafeManha) {

        if (cafeManha.getData().isBefore(LocalDate.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro, a data precisa ser maior que a atual.");
        }


        Optional<CafeManha> cafeExistente = CafeManhaRepository
                .findByCpfAndDataAndOpcaoCafe(cafeManha.getCpf(), cafeManha.getData(), cafeManha.getOpcaoCafe());

        if (cafeExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Já existe um registro para este CPF nesta data com a mesma opção de café.");
        }

        try {
            cafeManhaRepository.save(cafeManha);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registro de café da manhã adicionado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao adicionar registro de café da manhã.");
        }
    }

    public ResponseEntity<String> atualizarCafeManha(Long id, CafeManha cafeManha) {
        Optional<CafeManha> cafeExistente = cafeManhaRepository.findById(cafeManha.getId());

        if (cafeExistente.isPresent()) {
            try {
                CafeManha cafeAtualizado = cafeExistente.get();
                cafeAtualizado.setNomeColaborador(cafeManha.getNomeColaborador());
                cafeAtualizado.setCpf(cafeManha.getCpf());
                cafeAtualizado.setOpcaoCafe(cafeManha.getOpcaoCafe());
                cafeAtualizado.setData(cafeManha.getData());

                cafeManhaRepository.save(cafeAtualizado);

                return ResponseEntity.status(HttpStatus.OK).body("Registro de café da manhã atualizado com sucesso.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erro ao atualizar registro de café da manhã.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro de café da manhã não encontrado.");
        }
    }

    public ResponseEntity<String> excluirCafeManha(String cpf, LocalDate data) {
        Optional<CafeManha> cafeExistente = Optional.ofNullable(cafeManhaRepository.findByCpfAndData(cpf, data));

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


    public ResponseEntity<String> validarAtualizacaoCafeManha(Long id, CafeManha cafeManha) {
            return null;
    }

    public List<CafeManha> obterTodosCafesAtualizados() {

        return null;
    }

    public Optional<Colaborador> obterColaboradorPorId(Long id) {

        return null;
    }

    public ResponseEntity<String> excluirTodosColaboradores() {
                return null;
    }

    public ResponseEntity<String> excluirColaboradorPorId(Long id) {
        return null;
    }

    public Optional<CafeManha> obterCafePorId(Long id) {
        return null;
    }

    public ResponseEntity<String> excluirCafePorId(Long id) {
            return null;
    }
}


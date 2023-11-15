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

    @Autowired
    public ResponseEntity<String> adicionarCafe(CafeManha cafeManha) {
        String opcaoCafe = cafeManha.getOpcaoCafe();
        if (cafeManhaRepository.existsopcaoCafe(opcaoCafe)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Café já cadastrado");
        }
        try {
            cafeManhaRepository.save(cafeManha);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registro de café da manhã adicionado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao adicionar registro de café da manhã.");
        }
    }

    public ResponseEntity<String> atualizarCafeManha(Long id, CafeManha atualizarCafeManha) {
        Optional<CafeManha> cafeExistente = cafeManhaRepository.findById(id);

        if (cafeExistente.isPresent()) {

            CafeManha cafeAtualizado = cafeExistente.get();
            cafeAtualizado.setOpcaoCafe(atualizarCafeManha.getOpcaoCafe());


            cafeManhaRepository.save(cafeAtualizado);


            return ResponseEntity.status(HttpStatus.OK).body("Café da manhã atualizado com sucesso");
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Café da manhã não encontrado.");
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


}


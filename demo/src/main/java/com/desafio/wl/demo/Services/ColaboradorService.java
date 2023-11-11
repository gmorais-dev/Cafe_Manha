package com.desafio.wl.demo.Services;

import com.desafio.wl.demo.Model.CafeManha;
import com.desafio.wl.demo.Model.Colaborador;
import com.desafio.wl.demo.Repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;
    private final CafeManhaService cafeManhaService;

    @Autowired
    public ColaboradorService(ColaboradorRepository colaboradorRepository, CafeManhaService cafeManhaService) {
        this.colaboradorRepository = colaboradorRepository;
        this.cafeManhaService = cafeManhaService;
    }

    public ResponseEntity<String> adicionarColaboradorParaCafe(Long colaboradorId, CafeManha cafeManha) {
        Optional<Colaborador> colaboradorExistente = colaboradorRepository.findById(colaboradorId);

        if (colaboradorExistente.isPresent()) {
            try {
                cafeManha.setNomeColaborador(String.valueOf(colaboradorExistente.get()));
                cafeManhaService.adicionarCafeManha(cafeManha);
                return ResponseEntity.status(HttpStatus.CREATED).body("Café da Manhã associado ao Colaborador com sucesso");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erro ao associar Café da Manhã ao Colaborador.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colaborador não encontrado na nossa base de dados");
        }
    }

    private boolean validarCPF(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }

    public ResponseEntity<String> validarEAdicionarCafeManha(Long colaboradorId, CafeManha cafeManha) {
        Optional<Colaborador> colaboradorExistente = colaboradorRepository.findById(colaboradorId);

        if (colaboradorExistente.isPresent()) {
            try {
                String cpf = colaboradorExistente.get().getCpf();
                if (!validarCPF(cpf)) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao associar Café da Manhã ao Colaborador. CPF inválido.");
                }

                cafeManha.setNomeColaborador(colaboradorExistente.get().getNome());
                cafeManhaService.adicionarCafeManha(cafeManha);
                return ResponseEntity.status(HttpStatus.CREATED).body("Café da Manhã associado ao Colaborador com sucesso");

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erro ao associar Café da Manhã ao Colaborador.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colaborador não encontrado na nossa base de dados");
        }
    }
}

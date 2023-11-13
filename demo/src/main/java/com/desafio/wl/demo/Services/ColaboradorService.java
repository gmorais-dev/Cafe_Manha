package com.desafio.wl.demo.Services;

import com.desafio.wl.demo.Model.Colaborador;
import com.desafio.wl.demo.Repository.ColaboradorRepository;
import com.desafio.wl.demo.Repository.CafeManhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;
    private final CafeManhaRepository cafeManhaRepository;

    @Autowired
    public ColaboradorService(ColaboradorRepository colaboradorRepository, CafeManhaRepository cafeManhaRepository) {
        this.colaboradorRepository = colaboradorRepository;
        this.cafeManhaRepository = cafeManhaRepository;
    }

    public ResponseEntity<String> adicionarColaborador(Colaborador colaborador) {
        String cpf = colaborador.getCpf();
        if (colaboradorRepository.existsByCpf(cpf)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF já cadastrado.");
        }

        colaboradorRepository.save(colaborador);
        return ResponseEntity.status(HttpStatus.CREATED).body("Colaborador adicionado com sucesso.");
    }

    public ResponseEntity<String> excluirColaborador(Long id) {
        Optional<Colaborador> colaboradorExistente = colaboradorRepository.findById(id);

        if (colaboradorExistente.isPresent()) {
            String cpf = colaboradorExistente.get().getCpf();
            if (cafeManhaRepository.existsByCpf(cpf)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não é possível excluir. Café da manhã já registrado para este colaborador.");
            }

            colaboradorRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Colaborador excluído com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colaborador não encontrado.");
        }
    }

    public ResponseEntity<String> atualizarColaborador(Long id, Colaborador novoColaborador) {
        Optional<Colaborador> colaboradorExistente = colaboradorRepository.findById(id);

        if (colaboradorExistente.isPresent()) {
            Colaborador colaboradorAtualizado = colaboradorExistente.get();
            colaboradorAtualizado.setNome(novoColaborador.getNome());
            colaboradorAtualizado.setCpf(novoColaborador.getCpf());

            colaboradorRepository.save(colaboradorAtualizado);
            return ResponseEntity.status(HttpStatus.OK).body("Colaborador atualizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colaborador não encontrado.");
        }
    }

    public ResponseEntity<String> validarCPFExistente(String cpf) {
        if (colaboradorRepository.existsByCpf(cpf)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF já cadastrado.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("CPF disponível para cadastro.");
        }
    }

    public Optional<Object> buscarColaboradorPorId(Long id) {
            return null;
    }

    public List<Colaborador> listarColaboradores() {
        return null;
    }
}

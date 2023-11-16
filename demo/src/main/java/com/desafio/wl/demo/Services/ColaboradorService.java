package com.desafio.wl.demo.Services;

import com.desafio.wl.demo.Model.CafeManha;
import com.desafio.wl.demo.Model.Colaborador;
import com.desafio.wl.demo.Repository.CafeManhaRepository;
import com.desafio.wl.demo.Repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

    @Autowired
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

        Colaborador colaborador;
        if (colaboradorExistente.isPresent()) {
            colaborador = colaboradorExistente.get();

            if (temCafeDaManhaRegistrado(colaborador)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não é possível excluir. Café da manhã já registrado para este colaborador.");
            }

            colaboradorRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Colaborador excluído com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colaborador não encontrado.");
        }


    }

    private boolean temCafeDaManhaRegistrado(Colaborador colaborador) {
        String cpf = colaborador.getCpf();
        return cafeManhaRepository.existsById(colaborador.getId());

    }

    public String atualizarColaborador(Long id, Colaborador atualizaColaborador) {
        Optional<Colaborador> colaboradorExistente = colaboradorRepository.findById(id);

        if (colaboradorExistente.isPresent()) {
            Colaborador colaboradorAtualizado = colaboradorExistente.get();
            colaboradorAtualizado.setNome(atualizaColaborador.getNome());
            colaboradorAtualizado.setCpf(atualizaColaborador.getCpf());

            colaboradorRepository.save(colaboradorAtualizado);
            return "Colaborador atualizado com sucesso.";
        } else {
            return "Colaborador não encontrado.";
        }
    }

    public ResponseEntity<String> validarCPFExistente(String cpf) {
        if (colaboradorRepository.existsByCpf(cpf)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF já cadastrado.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("CPF disponível para cadastro.");
        }
    }

    public List<Colaborador> listarColaboradores() {
        List<Colaborador> listarColaboradores = colaboradorRepository.findAll();
        if (listarColaboradores.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listarColaboradores).getBody();
        } else {
            return ResponseEntity.ok().body(listarColaboradores).getBody();
        }
    }
}

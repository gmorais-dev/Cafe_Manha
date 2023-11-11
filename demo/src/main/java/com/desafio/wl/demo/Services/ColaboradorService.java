package com.desafio.wl.demo.Services;

import com.desafio.wl.demo.Model.CafeManha;
import com.desafio.wl.demo.Model.Colaborador;
import com.desafio.wl.demo.Repository.ColaboradorRepository;
import com.desafio.wl.demo.Repository.CafeManhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;
    private final CafeManhaRepository cafeManhaRepository; // Corrigido o nome do Repositório

    @Autowired
    public ColaboradorService(ColaboradorRepository colaboradorRepository, CafeManhaRepository cafeManhaRepository) {
        this.colaboradorRepository = colaboradorRepository;
        this.cafeManhaRepository = cafeManhaRepository;
    }

    public ResponseEntity<String> adicionarColaboradorParaCafe(Long colaboradorId, CafeManha cafeManha) {
        Optional<Colaborador> colaboradorExistente = colaboradorRepository.findById(colaboradorId);

        if (colaboradorExistente.isPresent()) {
            try {
                cafeManha.setNomeColaborador(String.valueOf(colaboradorExistente.get()));
                cafeManhaRepository.save(cafeManha); // Corrigido o nome do Repositório
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
                cafeManhaRepository.save(cafeManha); // Corrigido o nome do Repositório
                return ResponseEntity.status(HttpStatus.CREATED).body("Café da Manhã associado ao Colaborador com sucesso");

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erro ao associar Café da Manhã ao Colaborador.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colaborador não encontrado na nossa base de dados");
        }
    }

    public ResponseEntity<String> validarAtualizacaoCafeManha(Long id, CafeManha cafeManha) {
        Optional<CafeManha> cafeExistente = cafeManhaRepository.findById(id); // Corrigido o nome do Repositório

        if (cafeExistente.isPresent()) {
            Optional<CafeManha> mesmoItemNaData = CafeManhaRepository
                    .findByCpfAndDataAndOpcaoCafe(cafeManha.getCpf(), cafeManha.getData(), cafeManha.getOpcaoCafe());

            if (mesmoItemNaData.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Outro colaborador já cadastrou o mesmo item na mesma data.");
            }
        }

        return ResponseEntity.ok("Validação passou com sucesso.");
    }

    public ResponseEntity<String> atualizarCafeManha(Long id, CafeManha cafeManha) {
        Optional<CafeManha> cafeExistente = cafeManhaRepository.findById(id); // Corrigido o nome do Repositório

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
}

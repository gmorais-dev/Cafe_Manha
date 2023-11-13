package com.desafio.wl.demo.Controller;

import com.desafio.wl.demo.Model.Colaborador;
import com.desafio.wl.demo.Services.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colaboradores")
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    @Autowired
    public ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarColaborador(@RequestBody Colaborador colaborador) {
        return colaboradorService.adicionarColaborador(colaborador);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Colaborador>> listarColaboradores() {
        List<Colaborador> colaboradores = colaboradorService.listarColaboradores();
        return ResponseEntity.ok(colaboradores);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Object> buscarColaboradorPorId(@PathVariable Long id) {
        return colaboradorService.buscarColaboradorPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarColaborador(@PathVariable Long id, @RequestBody Colaborador novoColaborador) {
        return colaboradorService.atualizarColaborador(id, novoColaborador);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirColaborador(@PathVariable Long id) {
        return colaboradorService.excluirColaborador(id);
    }

    @GetMapping("/validar-cpf/{cpf}")
    public ResponseEntity<String> validarCPFExistente(@PathVariable String cpf) {
        return colaboradorService.validarCPFExistente(cpf);
    }
}

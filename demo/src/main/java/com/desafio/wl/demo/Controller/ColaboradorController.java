package com.desafio.wl.demo.Controller;
import com.desafio.wl.demo.DTO.ColaboradorDTO;

import com.desafio.wl.demo.Services.ColaboradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/colaboradores")

public class ColaboradorController {
    private final ColaboradorService colaboradorService;

    @PostMapping("/adicionar")
    public ColaboradorDTO adicionarColaborador(@RequestBody ColaboradorDTO colaboradordto){
        return colaboradorService.adicionarColaborador(colaboradordto);
    }
//    @CrossOrigin
//    @GetMapping("/listar")
//    @ResponseStatus(FOUND)
//    public ResponseEntity<List<Colaborador>> listarColaboradores(){
//        List<Colaborador> colaboradores = colaboradorService.listarColaboradores();
//        return ResponseEntity.ok(colaboradores);
//    }
//    @CrossOrigin
//    @PutMapping("/atualizar/{id}")
//    public ResponseEntity<String> atualizarColaborador(@PathVariable Long id, @RequestBody Colaborador atualizaColaborador) {
//        String mensagem = colaboradorService.atualizarColaborador(id, atualizaColaborador);
//        return ResponseEntity.ok(mensagem);
//    }
//       @CrossOrigin
//    @DeleteMapping("/excluir/{id}")
//    public ResponseEntity<String> excluirColaborador(@PathVariable Long id) {
//        return colaboradorService.excluirColaborador(id);
//    }
//
//    @GetMapping("/validar-cpf/{cpf}")
//    public ResponseEntity<String> validarCPFExistente(@PathVariable String cpf) {
//        return colaboradorService.validarCPFExistente(cpf);
//    }
}
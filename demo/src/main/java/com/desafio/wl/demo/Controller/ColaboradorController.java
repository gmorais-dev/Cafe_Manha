package com.desafio.wl.demo.Controller;
import com.desafio.wl.demo.Model.Colaborador;
import com.desafio.wl.demo.Repository.ColaboradorRepository;
import com.desafio.wl.demo.Services.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/colaboradores")
public class ColaboradorController {

    private final ColaboradorService colaboradorService;


    public ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }
    @CrossOrigin
    @PostMapping("/adicionar")
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionarColaborador(@RequestBody Colaborador colaborador){
        colaboradorService.adicionarColaborador(colaborador);
    }
    @CrossOrigin
    @GetMapping("/listar")
    @ResponseStatus(FOUND)
    public ResponseEntity<List<Colaborador>> listarColaboradores(){
        List<Colaborador> colaboradores = colaboradorService.listarColaboradores();
        return ResponseEntity.ok(colaboradores);
    }
    @CrossOrigin
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarColaborador(@PathVariable Long id, @RequestBody Colaborador atualizaColaborador) {
        String mensagem = colaboradorService.atualizarColaborador(id, atualizaColaborador);
        return ResponseEntity.ok(mensagem);
    }
       @CrossOrigin
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirColaborador(@PathVariable Long id) {
        return colaboradorService.excluirColaborador(id);
    }

    @GetMapping("/validar-cpf/{cpf}")
    public ResponseEntity<String> validarCPFExistente(@PathVariable String cpf) {
        return colaboradorService.validarCPFExistente(cpf);
    }
}
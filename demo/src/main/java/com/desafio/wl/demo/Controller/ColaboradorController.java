import com.desafio.wl.demo.Model.CafeManha;
import com.desafio.wl.demo.Services.CafeManhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cafe-da-manha")
public class CafeManhaController {

    private final CafeManhaService cafeManhaService;

    @Autowired
    public CafeManhaController(CafeManhaService cafeManhaService) {
        this.cafeManhaService = cafeManhaService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarCafeDaManha(@RequestBody CafeManha cafeManha) {
        ResponseEntity<String> response = cafeManhaService.adicionarCafeManha(cafeManha);
        HttpStatus status = (HttpStatus) response.getStatusCode();
        if (status.is2xxSuccessful()) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Café da Manhã adicionado com sucesso.");
        } else {
            return ResponseEntity.status(status).body(response.getBody());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarCafeManha(@PathVariable Long id, @RequestBody CafeManha cafeManha) {
        ResponseEntity<String> validacao = cafeManhaService.validarAtualizacaoCafeManha(id, cafeManha);
        if (validacao.getStatusCode() != HttpStatus.OK) {
            return validacao;
        }

        ResponseEntity<String> response = cafeManhaService.atualizarCafeManha(id, cafeManha);

        if (response.getStatusCode() == HttpStatus.OK) {
            List<CafeManha> updates = cafeManhaService.obterTodosCafesAtualizados();
            return ResponseEntity.status(response.getStatusCode()).body(updates);
        } else {
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        }
    }

    @GetMapping("/obter-por-id/{id}")
    public ResponseEntity<?> obterCafePorId(@PathVariable Long id) {
        Optional<CafeManha> cafeManha = cafeManhaService.obterCafePorId(id);
        return cafeManha.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Café da Manhã não encontrado."));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirCafePorId(@PathVariable Long id) {
        ResponseEntity<String> response = cafeManhaService.excluirCafePorId(id);

        if (response.getStatusCode() == HttpStatus.OK) {
            List<CafeManha> updates = cafeManhaService.obterTodosCafesAtualizados();
            return ResponseEntity.status(response.getStatusCode()).body(updates.toString());
        } else {
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        }
    }
}

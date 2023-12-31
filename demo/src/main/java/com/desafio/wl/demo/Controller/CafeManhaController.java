package com.desafio.wl.demo.Controller;

import com.desafio.wl.demo.Model.CafeManha;
import com.desafio.wl.demo.Model.Colaborador;
import com.desafio.wl.demo.Repository.CafeManhaRepository;
import com.desafio.wl.demo.Services.CafeManhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.FOUND;

@CrossOrigin
@RestController
@RequestMapping("/api/cafe-da-manha")
public class CafeManhaController {

    private final CafeManhaService cafeManhaService;


    public CafeManhaController(CafeManhaService cafeManhaService) {
        this.cafeManhaService = cafeManhaService;
    }

    @CrossOrigin
    @PostMapping("/adicionar")
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionarCafeDaManha(@RequestBody CafeManha cafeManha) {
        cafeManhaService.adicionarCafe(cafeManha);
    }

    @CrossOrigin
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarCafeManha(@RequestBody CafeManha atualizarCafeManha) {
        return cafeManhaService.atualizarCafeManha( atualizarCafeManha);
    }


    @CrossOrigin
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirCafeManha(@PathVariable long id) {
       return cafeManhaService.excluirCafeManha(id);
   }

    @CrossOrigin
    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<List<CafeManha>> listarCafeManha(){
     List<CafeManha> cafeManhaList = cafeManhaService.listarCafeManha().getBody();
        return ResponseEntity.ok(cafeManhaList);

    }


}



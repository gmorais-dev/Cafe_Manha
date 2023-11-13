package com.desafio.wl.demo.Controller;

import com.desafio.wl.demo.Model.CafeManha;
import com.desafio.wl.demo.Services.CafeManhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        return cafeManhaService.adicionarCafeManha(cafeManha);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarCafeManha(@PathVariable Long id, @RequestBody CafeManha cafeManha) {
        return cafeManhaService.atualizarCafeManha(id, cafeManha);
    }

    @DeleteMapping("/excluir/{cpf}/{data}")
    public ResponseEntity<String> excluirCafeManha(@PathVariable String cpf, @PathVariable String data) {
        LocalDate localDate = LocalDate.parse(data);
        return cafeManhaService.excluirCafeManha(cpf, localDate);
    }
}

package com.desafio.wl.demo.Controllers;

import com.desafio.wl.demo.Model.CafeManha;
import com.desafio.wl.demo.Services.CafeManhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

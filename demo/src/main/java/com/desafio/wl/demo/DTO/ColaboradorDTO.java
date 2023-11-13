package com.desafio.wl.demo.DTO;

public class ColaboradorDTO {
    private String nome;
    private String cpf;

    public ColaboradorDTO(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
    public ColaboradorDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}


package com.desafio.wl.demo.DTO;

public class CafeManhaDTO {
    private String nome;

    public String getNome() {
        return nome;
    }

    public CafeManhaDTO(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public CafeManhaDTO() {

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

    private String cpf;
}

package com.desafio.wl.demo.Model;

public class Colaborador {

    private long id;
    private String Nome;
    private String Cpf;

    public Colaborador(long id, String nome, String cpf) {
        this.id = id;
        Nome = nome;
        Cpf = cpf;
    }
    public Colaborador() {
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public static String getCpf() {
        return getCpf();
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }
}

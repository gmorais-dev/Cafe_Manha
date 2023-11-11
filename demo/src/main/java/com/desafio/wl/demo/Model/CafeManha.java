package com.desafio.wl.demo.Model;


import java.time.LocalDate;
import java.util.Date;

public class CafeManha {
    private long id;
    private String nomeColaborador;
    private String cpf;
    private String opcaoCafe;
    private LocalDate data;

    public CafeManha(long id, String nomeColaborador, String cpf, String opcaoCafe, LocalDate data) {
        this.id = id;
        this.nomeColaborador = nomeColaborador;
        this.cpf = cpf;
        this.opcaoCafe = opcaoCafe;
        this.data = data;
    }
    public CafeManha() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeColaborador() {
        return nomeColaborador;
    }

    public void setNomeColaborador(String nomeColaborador) {
        this.nomeColaborador = nomeColaborador;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getOpcaoCafe() {
        return opcaoCafe;
    }

    public void setOpcaoCafe(String opcaoCafe) {
        this.opcaoCafe = opcaoCafe;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}

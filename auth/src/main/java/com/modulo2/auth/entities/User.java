package com.modulo2.auth.entities;

import java.util.Date;

public class User {
    private Integer id;
    private String nome;
    private String password;
    private String email;
    private boolean ativo;
    private Date dtaNasc;

    public User(Integer id, String nome, String password, String email,Date dtaNasc, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.password = password;
        this.email = email;
        this.dtaNasc = dtaNasc;
        this.ativo = ativo;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public Date getDtaNasc() {
        return dtaNasc;
    }
    public void setDtaNasc(Date dtaNasc) {
        this.dtaNasc = dtaNasc;
    }
}

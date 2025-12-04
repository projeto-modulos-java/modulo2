package com.modulo2.auth.repositories.entities;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    private String password;
    
    private String email;
    private Date dtaNasc;
    private boolean ativo;

    public UserEntity() {}

    public UserEntity(Integer id, String nome, String password, String email, Date dtaNasc, boolean ativo) {
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
    public Date getDtaNasc() {
        return dtaNasc;
    }
    public void setDtaNasc(Date dtaNasc) {
        this.dtaNasc = dtaNasc;
    }
     public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}

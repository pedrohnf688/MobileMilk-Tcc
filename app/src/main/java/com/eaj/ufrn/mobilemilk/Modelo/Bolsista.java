package com.eaj.ufrn.mobilemilk.Modelo;

import com.eaj.ufrn.mobilemilk.Enum.EnumTipoPerfilUsuario;

import java.util.List;

public class Bolsista {

    private String nome;
    private String email;
    private String cpf;
    private String username;
    private String senha;
    private EnumTipoPerfilUsuario tipoPerfilUsuario;

    public Bolsista(String nome, String email, String cpf, String username, String senha, EnumTipoPerfilUsuario tipoPerfilUsuario) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.username = username;
        this.senha = senha;
        this.tipoPerfilUsuario = tipoPerfilUsuario;
    }

    public Bolsista(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public EnumTipoPerfilUsuario getTipoPerfilUsuario() {
        return tipoPerfilUsuario;
    }

    public void setTipoPerfilUsuario(EnumTipoPerfilUsuario tipoPerfilUsuario) {
        this.tipoPerfilUsuario = tipoPerfilUsuario;
    }
}

package com.eaj.ufrn.mobilemilk.Modelo;

import com.eaj.ufrn.mobilemilk.Enum.TipoPerfilUsuario;

public abstract class Usuario {

    private String nome;
    private String email;
    private String cpf;
    private TipoPerfilUsuario codigoTipoPerfilUsuario;

    public Usuario(String nome, String email, String cpf, TipoPerfilUsuario codigoTipoPerfilUsuario) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.codigoTipoPerfilUsuario = codigoTipoPerfilUsuario;
    }

    public Usuario(){

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

    public TipoPerfilUsuario getCodigoTipoPerfilUsuario() {
        return codigoTipoPerfilUsuario;
    }

    public void setCodigoTipoPerfilUsuario(TipoPerfilUsuario codigoTipoPerfilUsuario) {
        this.codigoTipoPerfilUsuario = codigoTipoPerfilUsuario;
    }
}
